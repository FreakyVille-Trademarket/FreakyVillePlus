package dk.fvtrademarket.fvplus.api.region;

import dk.fvtrademarket.fvplus.api.enums.FreakyVilleServer;
import dk.fvtrademarket.fvplus.api.enums.PrisonSector;
import dk.fvtrademarket.fvplus.api.util.BlockVector2;
import dk.fvtrademarket.fvplus.api.util.BlockVector3;
import org.spongepowered.include.com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public class PolygonalRegion extends Region {

  private ImmutableList<BlockVector2> points;
  private int minY;
  private int maxY;

  public PolygonalRegion(FreakyVilleServer server, String id, String displayName, List<BlockVector2> points, int minY, int maxY) {
    super(server, id, displayName);
    init((ImmutableList<BlockVector2>) points, minY, maxY);
  }

  public PolygonalRegion(PrisonSector sector, String id, String displayName, List<BlockVector2> points, int minY, int maxY) {
    super(sector, id, displayName);
    init((ImmutableList<BlockVector2>) points, minY, maxY);
  }

  private void init (ImmutableList<BlockVector2> points, int minY, int maxY) {
    ImmutableList<BlockVector2> immutablePoints = (ImmutableList<BlockVector2>) List.copyOf(points);
    setMinMaxPoints(immutablePoints, minY, maxY);
    this.points = immutablePoints;
    this.minY = min.y();
    this.maxY = max.y();
  }

  private void setMinMaxPoints(ImmutableList<BlockVector2> points2D, int minY, int maxY) {
    List<BlockVector3> points = new ArrayList<>();
    int y = minY;
    for (BlockVector2 point2D : points2D) {
      points.add(BlockVector3.at(point2D.x(), y, point2D.z()));
      y = maxY;
    }
    setMinMaxPoints(points);
  }

  @Override
  public boolean contains(BlockVector3 position) {
    int targetX = position.x();
    int targetY = position.y();
    int targetZ = position.z();

    if (targetY < minY || targetY > maxY) {
      return false;
    }

    if (targetX < min.x() || targetX > max.x() || targetZ < min.z() || targetZ > max.z()) {
      return false;
    }
    boolean inside = false;
    int npoints = points.size();
    int xNew, zNew;
    int xOld, zOld;
    int x1, z1;
    int x2, z2;
    long crossproduct;
    int i;

    xOld = points.get(npoints - 1).x();
    zOld = points.get(npoints - 1).z();

    for (i = 0; i < npoints; i++) {
      xNew = points.get(i).x();
      zNew = points.get(i).z();
      if (xNew == targetX && zNew == targetZ) {
        return true;
      }
      if (xNew > xOld) {
        x1 = xOld;
        x2 = xNew;
        z1 = zOld;
        z2 = zNew;
      } else {
        x1 = xNew;
        x2 = xOld;
        z1 = zNew;
        z2 = zOld;
      }
      if (x1 <= targetX && targetX <= x2) {
        crossproduct = ((long) targetZ - (long) z1) * (long) (x2 - x1)
            - ((long) z2 - (long) z1) * (long) (targetX - x1);
        if (crossproduct == 0) {
          if ((z1 <= targetZ) == (targetZ <= z2)) return true;
        } else if (crossproduct < 0 && (x1 != targetX)) {
          inside = !inside;
        }
      }
      xOld = xNew;
      zOld = zNew;
    }

    return inside;
  }
}
