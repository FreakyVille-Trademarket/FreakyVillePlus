package org.champenslabyaddons.fvplus.module.nprison;

import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.chat.command.CommandService;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.EventBus;
import net.labymod.api.util.I18n;
import net.labymod.api.util.logging.Logging;
import org.champenslabyaddons.fvplus.commands.timers.TimerCommand;
import org.champenslabyaddons.fvplus.configuration.PrisonSubConfiguration;
import org.champenslabyaddons.fvplus.connection.ClientInfo;
import org.champenslabyaddons.fvplus.internal.PoiList;
import org.champenslabyaddons.fvplus.listeners.nprison.PoiListener;
import org.champenslabyaddons.fvplus.module.CombinedModule;
import org.champenslabyaddons.fvplus.module.ListenerModule;
import org.champenslabyaddons.fvplus.poi.POI;
import org.champenslabyaddons.fvplus.util.WidgetUpdater;
import org.champenslabyaddons.fvplus.widgets.PoiTimerWidget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoiModule extends CombinedModule {
  private final ClientInfo clientInfo;
  private final HudWidgetRegistry hudWidgetRegistry;
  private final PoiList poiList;
  private final List<PoiTimerWidget> poiTimerWidgets;
  private final PrisonSubConfiguration prisonSubConfiguration;

  public PoiModule(CommandService commandService, EventBus eventBus, ClientInfo clientInfo, HudWidgetRegistry hudWidgetRegistry, PoiList poiList,
      PrisonSubConfiguration prisonSubConfiguration) {
    super(commandService, eventBus);
    this.clientInfo = clientInfo;
    this.hudWidgetRegistry = hudWidgetRegistry;
    this.poiList = poiList;
    try {
      poiList.init();
    } catch (IOException e) {
      Logging.getLogger().error(I18n.translate("fvplus.logging.error.loadingPoi"), e);
    }
    this.prisonSubConfiguration = prisonSubConfiguration;
    this.moduleCommands = moduleCommandsOverview();
    this.moduleListeners = moduleListenersOverview();
    this.poiTimerWidgets = modulePoiWidgets();
  }

  @Override
  public void register() {
    super.register();
    for (PoiTimerWidget poiTimerWidget : poiTimerWidgets) {
      hudWidgetRegistry.register(poiTimerWidget);
    }
  }

  @Override
  public void unregister() {
    super.unregister();
    for (PoiTimerWidget poiTimerWidget : poiTimerWidgets) {
      hudWidgetRegistry.unregister(poiTimerWidget.getId());
    }
  }

  @Override
  protected ArrayList<Command> moduleCommandsOverview() {
    ArrayList<Command> commands = new ArrayList<>();
    commands.add(new TimerCommand(clientInfo, poiList));
    return commands;
  }

  @Override
  protected ArrayList<Object> moduleListenersOverview() {
    ArrayList<Object> listeners = new ArrayList<>();
    listeners.add(new PoiListener(clientInfo, poiList));
    listeners.add(new WidgetUpdater(hudWidgetRegistry));
    return listeners;
  }

  private ArrayList<PoiTimerWidget> modulePoiWidgets() {
    ArrayList<PoiTimerWidget> widgets = new ArrayList<>();
    for (POI poi : poiList.getPois()) {
      widgets.add(new PoiTimerWidget(poi, clientInfo, Icon.sprite16(
          ResourceLocation.create("fvplus",
              "themes/vanilla/textures/settings/icons.png"), 2, 0)));
    }
    return widgets;
  }

  @Override
  public boolean shouldRegisterAutomatically() {
    return prisonSubConfiguration.getEnabledPoiModule().get();
  }
}
