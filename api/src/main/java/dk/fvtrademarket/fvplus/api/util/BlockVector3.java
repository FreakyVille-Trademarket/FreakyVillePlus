package dk.fvtrademarket.fvplus.api.util;

/**
 * En 3D vektor med heltalskoordinater til at vurdere præcise positioner i Minecraft.
 * <p>
 *   Denne klasse er originalt fra WorldEdit
 * </p>
 * @param x koordinat
 * @param y koordinat
 * @param z koordinat
 */
public record BlockVector3(int x, int y, int z) {
  public static final BlockVector3 ZERO = new BlockVector3(0, 0, 0);
  public static final BlockVector3 ONE = new BlockVector3(1, 1, 1);

  public static BlockVector3 at(double x, double y, double z) {
    return at((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
  }

  public static BlockVector3 at(int x, int y, int z) {
    switch (y) {
      case 0:
        if (x == 0 && z == 0) {
          return ZERO;
        }
        break;
      case 1:
        if (x == 1 && z == 1) {
          return ONE;
        }
        break;
      default:
        break;
    }
    return new BlockVector3(x, y, z);
  }

  /**
   * Sæt X koordinatet.
   *
   * @param x den nye X
   * @return en ny vektor
   */
  public BlockVector3 withX(int x) {
    return BlockVector3.at(x, y, z);
  }

  /**
   * Sæt Y koordinatet.
   *
   * @param y den nye Y
   * @return en ny vektor
   */
  public BlockVector3 withY(int y) {
    return BlockVector3.at(x, y, z);
  }

  /**
   * Sæt Z koordinatet.
   *
   * @param z den nye Z
   * @return en ny vektor
   */
  public BlockVector3 withZ(int z) {
    return BlockVector3.at(x, y, z);
  }

  /**
   * Læg en anden vektor til denne vektor og returner resultatet som en ny vektor.
   *
   * @param other den anden vektor
   * @return en ny vektor
   */
  public BlockVector3 add(BlockVector3 other) {
    return add(other.x, other.y, other.z);
  }

  /**
   * Læg en anden vektor til denne vektor og returner resultatet som en ny vektor.
   *
   * @param x værdien der skal lægges til
   * @param y værdien der skal lægges til
   * @param z værdien der skal lægges til
   * @return en ny vektor
   */
  public BlockVector3 add(int x, int y, int z) {
    return BlockVector3.at(this.x + x, this.y + y, this.z + z);
  }

  /**
   * Læg en liste af vektorer til denne vektor og returner resultatet som en ny vektor.
   *
   * @param others et array af vektorer
   * @return en ny vektor
   */
  public BlockVector3 add(BlockVector3... others) {
    int newX = x;
    int newY = y;
    int newZ = z;

    for (BlockVector3 other : others) {
      newX += other.x;
      newY += other.y;
      newZ += other.z;
    }

    return BlockVector3.at(newX, newY, newZ);
  }

  /**
   * Træk en anden vektor fra denne vektor og returner resultatet som en ny vektor.
   *
   * @param other den anden vektor
   * @return en ny vektor
   */
  public BlockVector3 subtract(BlockVector3 other) {
    return subtract(other.x, other.y, other.z);
  }

  /**
   * Træk en anden vektor fra denne vektor og returner resultatet som en ny vektor.
   *
   * @param x værdien der skal trækkes fra
   * @param y værdien der skal trækkes fra
   * @param z værdien der skal trækkes fra
   * @return en ny vektor
   */
  public BlockVector3 subtract(int x, int y, int z) {
    return BlockVector3.at(this.x - x, this.y - y, this.z - z);
  }

  /**
   * Træk en liste af vektorer fra denne vektor og returner resultatet som en ny vektor.
   *
   * @param others et array af vektorer
   * @return en ny vektor
   */
  public BlockVector3 subtract(BlockVector3... others) {
    int newX = x;
    int newY = y;
    int newZ = z;

    for (BlockVector3 other : others) {
      newX -= other.x;
      newY -= other.y;
      newZ -= other.z;
    }

    return BlockVector3.at(newX, newY, newZ);
  }

  /**
   * Ganger denne vektor med en anden vektor på hver komponent.
   *
   * @param other den anden vektor
   * @return en ny vektor
   */
  public BlockVector3 multiply(BlockVector3 other) {
    return multiply(other.x, other.y, other.z);
  }

  /**
   * Ganger denne vektor med en anden vektor på hver komponent.
   *
   * @param x værdien der skal ganges med
   * @param y værdien der skal ganges med
   * @param z værdien der skal ganges med
   * @return en ny vektor
   */
  public BlockVector3 multiply(int x, int y, int z) {
    return BlockVector3.at(this.x * x, this.y * y, this.z * z);
  }

  /**
   * Ganger denne vektor med nul eller flere vektorer på hver komponent.
   *
   * @param others et array af vektorer
   * @return en ny vektor
   */
  public BlockVector3 multiply(BlockVector3... others) {
    int newX = x;
    int newY = y;
    int newZ = z;

    for (BlockVector3 other : others) {
      newX *= other.x;
      newY *= other.y;
      newZ *= other.z;
    }

    return BlockVector3.at(newX, newY, newZ);
  }

  /**
   * Udfør skalar multiplikation og returner en ny vektor.
   *
   * @param n værdien der skal ganges med
   * @return en ny vektor
   */
  public BlockVector3 multiply(int n) {
    return multiply(n, n, n);
  }

  /**
   * Divider denne vektor med en anden vektor på hver komponent.
   *
   * @param other den anden vektor
   * @return en ny vektor
   */
  public BlockVector3 divide(BlockVector3 other) {
    return divide(other.x, other.y, other.z);
  }

  /**
   * Divider denne vektor med nogle x, y og z koordinater.
   *
   * @param x værdien der skal divideres med
   * @param y værdien der skal divideres med
   * @param z værdien der skal divideres med
   * @return en ny vektor
   */
  public BlockVector3 divide(int x, int y, int z) {
    return BlockVector3.at(this.x / x, this.y / y, this.z / z);
  }

  /**
   * Udfør skalar division og returner en ny vektor.
   *
   * @param n værdien der skal divideres med
   * @return en ny vektor
   */
  public BlockVector3 divide(int n) {
    return divide(n, n, n);
  }

  /**
   * Finder længden af vektoren.
   *
   * @return længden
   */
  public double length() {
    return Math.sqrt(lengthSq());
  }

  /**
   * Finder længden af vektoren, kvadreret.
   *
   * @return længden, kvadreret
   */
  public int lengthSq() {
    return x * x + y * y + z * z;
  }

  /**
   * Finder afstanden mellem denne vektor og en anden vektor.
   *
   * @param other den anden vektor
   * @return afstanden
   */
  public double distance(BlockVector3 other) {
    return Math.sqrt(distanceSq(other));
  }

  /**
   * Finder afstanden mellem denne vektor og en anden vektor, kvadreret.
   *
   * @param other den anden vektor
   * @return afstanden, kvadreret
   */
  public int distanceSq(BlockVector3 other) {
    int dx = other.x - x;
    int dy = other.y - y;
    int dz = other.z - z;
    return dx * dx + dy * dy + dz * dz;
  }

  /**
   * Tjekker for om en vektor er indeholdt i en anden vektor.
   *
   * @param min det mindste punkt (X, Y og Z er laveste)
   * @param max det største punkt (X, Y og Z er laveste)
   * @return om vektoren er indeholdt
   */
  public boolean containedWithin(BlockVector3 min, BlockVector3 max) {
    return x >= min.x && x <= max.x && y >= min.y && y <= max.y && z >= min.z && z <= max.z;
  }

  /**
   * Få denne vektors pitch som brugt i spillet.
   *
   * @return pitch i radianer
   */
  public double toPitch() {
    double x = this.x;
    double z = this.z;

    if (x == 0 && z == 0) {
      return y > 0 ? -90 : 90;
    } else {
      double x2 = x * x;
      double z2 = z * z;
      double xz = Math.sqrt(x2 + z2);
      return Math.toDegrees(Math.atan(-y / xz));
    }
  }

  /**
   * Få denne vektors yaw som brugt i spillet.
   *
   * @return yaw i radianer
   */
  public double toYaw() {
    double x = this.x;
    double z = this.z;

    double t = Math.atan2(-x, z);
    double tau = 2 * Math.PI;

    return Math.toDegrees(((t + tau) % tau));
  }

  /**
   * Finder minimumskomponenterne af to vektorer.
   *
   * @param v2 den anden vektor
   * @return minimum
   */
  public BlockVector3 getMinimum(BlockVector3 v2) {
    return new BlockVector3(
        Math.min(x, v2.x),
        Math.min(y, v2.y),
        Math.min(z, v2.z)
    );
  }

  /**
   * Finder maksimumskomponenterne af to vektorer.
   *
   * @param v2 den anden vektor
   * @return maximum
   */
  public BlockVector3 getMaximum(BlockVector3 v2) {
    return new BlockVector3(
        Math.max(x, v2.x),
        Math.max(y, v2.y),
        Math.max(z, v2.z)
    );
  }

  /**
   * Opretter en 2D vektor ved at fjerne Y komponenten fra denne vektor.
   *
   * @return en ny {@link BlockVector2}
   */
  public BlockVector2 toBlockVector2() {
    return BlockVector2.at(x, z);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ", " + z + ")";
  }
}
