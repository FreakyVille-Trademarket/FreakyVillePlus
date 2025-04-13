package dk.fvtrademarket.fvplus.api.region;

import dk.fvtrademarket.fvplus.api.enums.FreakyVilleServer;
import dk.fvtrademarket.fvplus.api.enums.PrisonSector;
import dk.fvtrademarket.fvplus.api.util.BlockVector3;
import java.util.List;

public class CuboidRegion extends Region {

  public CuboidRegion(FreakyVilleServer server, String id, String displayName, BlockVector3 min, BlockVector3 max) {
    super(server, id, displayName);
    this.setMinMaxPoints(min, max);
  }

  public CuboidRegion(PrisonSector sector, String id, String displayName, BlockVector3 min, BlockVector3 max) {
    super(sector, id, displayName);
    this.setMinMaxPoints(min, max);
  }

  private void setMinMaxPoints(BlockVector3 min, BlockVector3 max) {
    this.setMinMaxPoints(List.of(min, max));
  }

  @Override
  public boolean contains(BlockVector3 pt) {
    final double x = pt.x();
    final double y = pt.y();
    final double z = pt.z();
    return x >= min.x() && x < max.x() + 1
        && y >= min.y() && y < max.y() + 1
        && z >= min.z() && z < max.z() + 1;
  }


}
