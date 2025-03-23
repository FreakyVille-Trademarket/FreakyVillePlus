package dk.fvtrademarket.fvplus.core.event;

import dk.fvtrademarket.fvplus.api.util.BlockVector3;
import net.labymod.api.event.Event;

public class CreateLocationWaypointEvent implements Event {
  private final String displayName;
  private final BlockVector3 requestedLocation;

  public CreateLocationWaypointEvent(String displayName, BlockVector3 requestedLocation) {
    this.displayName = displayName;
    this.requestedLocation = requestedLocation;
  }

  public String getDisplayName() {
    return displayName;
  }

  public BlockVector3 getRequestedLocation() {
    return requestedLocation;
  }
}
