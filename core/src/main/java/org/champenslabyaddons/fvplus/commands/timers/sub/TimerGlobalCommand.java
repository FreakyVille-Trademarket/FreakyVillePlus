package org.champenslabyaddons.fvplus.commands.timers.sub;

import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.util.I18n;
import org.champenslabyaddons.fvplus.commands.FreakyVillePlusSubCommand;
import org.champenslabyaddons.fvplus.connection.ClientInfo;
import org.champenslabyaddons.fvplus.internal.PoiList;
import org.champenslabyaddons.fvplus.poi.POI;
import org.champenslabyaddons.fvplus.util.FreakyVilleServer;
import java.util.ArrayList;
import java.util.List;

public class TimerGlobalCommand extends TimerSubCommand {
  private final String unavailable;

  public TimerGlobalCommand(String serverAndCategoryKey, String parentPrefix, ClientInfo clientInfo, PoiList poiList) {
    super("global", serverAndCategoryKey,  parentPrefix, clientInfo, poiList, "globale", "g");
    this.unavailable = I18n.translate("fvplus.widgets.timer.timeLeft.unavailable");
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (!clientInfo.isOnFreakyVille()) return false;
    List<POI> poisToShow = new ArrayList<>();
    for (POI poi : poiList.getPois()) {
      if (poi.getTimeLeft(clientInfo) == null) {
        continue;
      }
      if (poi.getTimeLeft(clientInfo).equals(unavailable)) {
        continue;
      }
      poisToShow.add(poi);
    }
    return howToExecute(arguments, poisToShow);
  }

  @Override
  protected void displayPOI(POI poi) {
    Component line = Component.text(" - ", NamedTextColor.GRAY);
    Component poiName = Component.text(poi.getDisplayName(), NamedTextColor.YELLOW);
    Component separator = Component.text(": ", NamedTextColor.GRAY);
    Component timeLeft = Component.text(poi.getTimeLeft(clientInfo), NamedTextColor.WHITE);
    displayMessage(line.append(poiName).append(separator).append(timeLeft));
  }
}
