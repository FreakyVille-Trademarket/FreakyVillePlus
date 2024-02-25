package org.champenslabyaddons.fvp.event;

import net.labymod.api.event.Event;
import org.champenslabyaddons.fvp.util.Location;

public class CreateLocationWaypointEvent implements Event {
  private final String displayName;
  private final Location requestedLocation;

  public CreateLocationWaypointEvent(String displayName, Location requestedLocation) {
    this.displayName = displayName;
    this.requestedLocation = requestedLocation;
  }

  public String getDisplayName() {
    return displayName;
  }

  public Location getRequestedLocation() {
    return requestedLocation;
  }
}
