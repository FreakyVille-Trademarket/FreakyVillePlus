package org.champenslabyaddons.fvplus;

import net.labymod.api.Laby;
import net.labymod.api.LabyAPI;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.chat.command.CommandService;
import net.labymod.api.event.EventBus;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.util.I18n;
import org.champenslabyaddons.fvplus.commands.internal.CheckRollCommand;
import org.champenslabyaddons.fvplus.commands.timers.TimerCommand;
import org.champenslabyaddons.fvplus.connection.ClientInfo;
import org.champenslabyaddons.fvplus.integrations.WaypointsIntegration;
import org.champenslabyaddons.fvplus.internal.PoiList;
import org.champenslabyaddons.fvplus.listeners.internal.PrisonNavigationListener;
import org.champenslabyaddons.fvplus.listeners.internal.ScoreBoardListener;
import org.champenslabyaddons.fvplus.listeners.internal.ServerNavigationListener;
import org.champenslabyaddons.fvplus.listeners.internal.ModuleListener;
import org.champenslabyaddons.fvplus.module.ModuleService;
import org.champenslabyaddons.fvplus.module.general.RPCModule;
import org.champenslabyaddons.fvplus.module.nprison.NPrisonModule;
import org.champenslabyaddons.fvplus.util.Messaging;

@AddonMain
public class FreakyVilleAddon extends LabyAddon<FreakyVillePlusConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
    LabyAPI labyAPI = this.labyAPI();
    ClientInfo clientInfo = new ClientInfo(labyAPI.serverController(), labyAPI.minecraft().getClientPlayer());
    EventBus eventBus = labyAPI.eventBus();
    CommandService commandService = labyAPI.commandService();
    Messaging.setExecutor(labyAPI.minecraft().chatExecutor());
    PoiList poiList = new PoiList();

    Laby.references().addonIntegrationService()
        .registerIntegration("labyswaypoints", WaypointsIntegration.class);
    this.registerListener(new ScoreBoardListener(clientInfo));
    this.registerListener(new ServerNavigationListener(clientInfo));
    this.registerListener(new PrisonNavigationListener(clientInfo));

    this.registerCommand(new CheckRollCommand(clientInfo));

    ModuleService moduleService = new ModuleService(this.logger());
    moduleService.registerModules(
        new RPCModule(eventBus, clientInfo, labyAPI(), configuration().getDiscordSubSettings()),
        new NPrisonModule(moduleService, commandService, eventBus, clientInfo, configuration().getPrisonSubSettings(), poiList)
    );

    this.registerListener(new ModuleListener(moduleService));

    this.logger().info(I18n.translate("fvplus.logging.enabled"));
  }

  @Override
  protected Class<FreakyVillePlusConfiguration> configurationClass() {
    return FreakyVillePlusConfiguration.class;
  }
}
