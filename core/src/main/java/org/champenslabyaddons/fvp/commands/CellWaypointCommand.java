package org.champenslabyaddons.fvp.commands;

import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import org.champenslabyaddons.fvp.event.CreateLocationWaypointEvent;
import org.champenslabyaddons.fvp.integrations.WaypointsIntegration;
import org.champenslabyaddons.fvp.internal.CellList;
import org.champenslabyaddons.fvp.objects.CellBlock;

public class CellWaypointCommand extends Command {

  private final CellList cellList;

  public CellWaypointCommand(CellList cellList) {
    super("ce");
    this.cellList = cellList;
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (arguments.length != 2) {
      return false;
    }
    if (!arguments[0].equalsIgnoreCase("waypoint")
        && !arguments[0].equalsIgnoreCase("w")) {
      return false;
    }
    if (!WaypointsIntegration.isEnabled()) {
      displayMessage(Component.translatable("fvp.server.prison.cell.commands.waypoint.disabled",
          NamedTextColor.RED));
      return true;
    }
    if (!cellList.isCellListed(arguments[1])) {
      displayMessage(Component.translatable("fvp.server.prison.cell.commands.waypoint.notListed",
          NamedTextColor.RED));
      return true;
    }
    if (cellList.getCorrespondingCellBlock(arguments[1]).isEmpty()) {
      displayMessage(Component.translatable("fvp.server.prison.cell.commands.waypoint.emptyResult",
          NamedTextColor.RED));
      return true;
    }
    CellBlock cellBlock = cellList.getCorrespondingCellBlock(arguments[1]).get();
    Laby.fireEvent(new CreateLocationWaypointEvent(cellBlock.getLocationDescription(), cellBlock.getMinecraftLocation()));
    displayMessage(Component.translatable("fvp.server.prison.cell.commands.waypoint.success",
      NamedTextColor.GREEN));
    return true;
  }
}