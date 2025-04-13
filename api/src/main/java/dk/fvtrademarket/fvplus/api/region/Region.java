package dk.fvtrademarket.fvplus.api.region;

import dk.fvtrademarket.fvplus.api.enums.FreakyVilleServer;
import dk.fvtrademarket.fvplus.api.enums.PrisonSector;
import dk.fvtrademarket.fvplus.api.util.BlockVector3;

import java.util.List;

public abstract class Region {

  protected final FreakyVilleServer associatedServer;
  protected PrisonSector associatedSector = null;

  protected final String id;
  protected final String displayName;

  protected BlockVector3 min;
  protected BlockVector3 max;

  protected int weight;

  Region(FreakyVilleServer server, String id, String displayName) {
    this.associatedServer = server;
    this.id = id;
    this.displayName = displayName;
  }

  Region(PrisonSector sector, String id, String displayName) {
      this.associatedServer = FreakyVilleServer.PRISON;
      this.associatedSector = sector;
      this.id = id;
      this.displayName = displayName;
  }

  protected void setMinMaxPoints(List<BlockVector3> points) {
    int minX = points.getFirst().x();
    int minY = points.getFirst().y();
    int minZ = points.getFirst().z();
    int maxX = minX;
    int maxY = minY;
    int maxZ = minZ;

    for (BlockVector3 v : points) {
      int x = v.x();
      int y = v.y();
      int z = v.z();

      if (x < minX) minX = x;
      if (y < minY) minY = y;
      if (z < minZ) minZ = z;

      if (x > maxX) maxX = x;
      if (y > maxY) maxY = y;
      if (z > maxZ) maxZ = z;
    }

    min = BlockVector3.at(minX, minY, minZ);
    max = BlockVector3.at(maxX, maxY, maxZ);
  }

  public abstract boolean contains(BlockVector3 point);

  public boolean contains(int x, int y, int z) {
      return contains(BlockVector3.at(x, y, z));
  }

  public BlockVector3 getMinimumPoint() {
    return min;
  }

  public BlockVector3 getMaximumPoint() {
    return max;
  }

  /**
   * Returnerer vægten af regionen. Vægten bruges til at bestemme hvilken region der skal prioriteres i visningen.
   *
   * @return vægten
   */
  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
}
