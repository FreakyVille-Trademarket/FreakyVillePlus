package org.champenslabyaddons.fvplus.module.nprison;

import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.chat.command.CommandService;
import net.labymod.api.event.EventBus;
import net.labymod.api.util.I18n;
import net.labymod.api.util.logging.Logging;
import org.champenslabyaddons.fvplus.commands.CellWaypointCommand;
import org.champenslabyaddons.fvplus.configuration.PrisonSubConfiguration;
import org.champenslabyaddons.fvplus.connection.ClientInfo;
import org.champenslabyaddons.fvplus.internal.CellList;
import org.champenslabyaddons.fvplus.listeners.nprison.CellListener;
import org.champenslabyaddons.fvplus.module.CombinedModule;
import java.io.IOException;
import java.util.ArrayList;

public class CellModule extends CombinedModule {

  private final ClientInfo clientInfo;
  private final CellList cellList;
  private final PrisonSubConfiguration prisonSubConfiguration;

  public CellModule(CommandService commandService, EventBus eventBus, ClientInfo clientInfo,
      PrisonSubConfiguration prisonSubConfiguration) {
    super(commandService, eventBus);
    this.clientInfo = clientInfo;
    this.cellList = new CellList();
    try {
      cellList.init();
    } catch (IOException e) {
      Logging.getLogger().error(I18n.translate("fvplus.logging.error.loadingCells"), e);
    }
    this.prisonSubConfiguration = prisonSubConfiguration;
    this.moduleCommands = moduleCommandsOverview();
    this.moduleListeners = moduleListenersOverview();
  }

  @Override
  protected ArrayList<Command> moduleCommandsOverview() {
    ArrayList<Command> commands = new ArrayList<>();
    commands.add(new CellWaypointCommand(clientInfo, cellList));
    return commands;
  }

  @Override
  protected ArrayList<Object> moduleListenersOverview() {
    ArrayList<Object> listeners = new ArrayList<>();
    listeners.add(new CellListener(clientInfo, cellList));
    return listeners;
  }

  @Override
  public boolean shouldRegisterAutomatically() {
    return prisonSubConfiguration.getEnabledCellModule().get();
  }
}
