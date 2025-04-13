package dk.fvtrademarket.fvplus.api.util;

import java.util.Comparator;

/**
 * En 2D vektor med heltalskoordinater til at vurdere præcise positioner i planen.
 * <p>
 *   Denne klasse er originalt fra WorldEdit
 * </p>
 * @param x koordinat
 * @param z koordinat
 */
public record BlockVector2(int x, int z) {

  public static final BlockVector2 ZERO = new BlockVector2(0, 0);
  public static final BlockVector2 ONE = new BlockVector2(1, 1);

  /**
   * En comparator for BlockVector2, der sorterer vektorerne efter rækker, med x som kolonne og z som række.
   *
   * <p>
   * For eksempel, hvis x er den vandrette akse og z er den lodrette akse, sorterer den således:
   * </p>
   *
   * <pre>
   * 0123
   * 4567
   * 90ab
   * cdef
   * </pre>
   */
  public static final Comparator<BlockVector2> COMPARING_GRID_ARRANGEMENT =
      Comparator.comparingInt(BlockVector2::z).thenComparingInt(BlockVector2::x);

  public static BlockVector2 at(double x, double z) {
    return at((int) Math.floor(x), (int) Math.floor(z));
  }

  public static BlockVector2 at(int x, int z) {
    switch (x) {
      case 0:
        if (z == 0) {
          return ZERO;
        }
        break;
      case 1:
        if (z == 1) {
          return ONE;
        }
        break;
      default:
        break;
    }
    return new BlockVector2(x, z);
  }

  /**
   * Sæt X-koordinatet.
   *
   * @param x det nye X
   * @return en ny vektor
   */
  public BlockVector2 withX(int x) {
    return BlockVector2.at(x, z);
  }

  /**
   * Sæt Z-koordinaten.
   *
   * @param z det nye Z
   * @return en ny vektor
   */
  public BlockVector2 withZ(int z) {
    return BlockVector2.at(x, z);
  }

  /**
   * Adderer en anden vektor til denne vektor og returnerer resultatet som en ny vektor.
   *
   * @param other den anden vektor
   * @return en ny vektor
   */
  public BlockVector2 add(BlockVector2 other) {
    return add(other.x, other.z);
  }

  /**
   * Adderer et x og z koordinat til denne vektor og returnerer resultatet som en ny vektor.
   *
   * @param x værdien der skal tilføjes
   * @param z værdien der skal tilføjes
   * @return en ny vektor
   */
  public BlockVector2 add(int x, int z) {
    return BlockVector2.at(this.x + x, this.z + z);
  }

  /**
   * Adderer flere vektorer til denne vektor og returnerer resultatet som en ny vektor.
   *
   * @param others et array af vektorer
   * @return en ny vektor
   */
  public BlockVector2 add(BlockVector2... others) {
    int newX = x;
    int newZ = z;

    for (BlockVector2 other : others) {
      newX += other.x;
      newZ += other.z;
    }

    return BlockVector2.at(newX, newZ);
  }

  /**
   * Trækker en anden vektor fra denne vektor og returnerer resultatet som en ny vektor.
   *
   * @param other den anden vektor
   * @return en ny vektor
   */
  public BlockVector2 subtract(BlockVector2 other) {
    return subtract(other.x, other.z);
  }

  /**
   * Trækker et x og z koordinat fra denne vektor og returnerer resultatet som en ny vektor.
   *
   * @param x værdien der skal trækkes fra
   * @param z værdien der skal trækkes fra
   * @return en ny vektor
   */
  public BlockVector2 subtract(int x, int z) {
    return BlockVector2.at(this.x - x, this.z - z);
  }

  /**
   * Trækker flere vektorer fra denne vektor og returnerer resultatet som en ny vektor.
   *
   * @param others et array af vektorer
   * @return en ny vektor
   */
  public BlockVector2 subtract(BlockVector2... others) {
    int newX = x;
    int newZ = z;

    for (BlockVector2 other : others) {
      newX -= other.x;
      newZ -= other.z;
    }

    return BlockVector2.at(newX, newZ);
  }

  /**
   * Ganger denne vektor med en anden vektor på hver komponent.
   *
   * @param other den anden vektor
   * @return en ny vektor
   */
  public BlockVector2 multiply(BlockVector2 other) {
    return multiply(other.x, other.z);
  }

  /**
   * Ganger denne vektor med nogle x og z koordinater.
   *
   * @param x værdien der skal ganges med
   * @param z værdien der skal ganges med
   * @return en ny vektor
   */
  public BlockVector2 multiply(int x, int z) {
    return BlockVector2.at(this.x * x, this.z * z);
  }

  /**
   * Ganger denne vektor med nul eller flere vektorer på hver komponent.
   *
   * @param others et array af vektorer
   * @return en ny vektor
   */
  public BlockVector2 multiply(BlockVector2... others) {
    int newX = x;
    int newZ = z;

    for (BlockVector2 other : others) {
      newX *= other.x;
      newZ *= other.z;
    }

    return BlockVector2.at(newX, newZ);
  }

  /**
   * Udfør skalar multiplikation og returner en ny vektor.
   *
   * @param n værdien der skal ganges med
   * @return en ny vektor
   */
  public BlockVector2 multiply(int n) {
    return multiply(n, n);
  }

  /**
   * Divider denne vektor med en anden vektor på hver komponent.
   *
   * @param other den anden vektor
   * @return en ny vektor
   */
  public BlockVector2 divide(BlockVector2 other) {
    return divide(other.x, other.z);
  }

  /**
   * Divider denne vektor med nogle x og z koordinater.
   *
   * @param x værdien der skal divideres med
   * @param z værdien der skal divideres med
   * @return en ny vektor
   */
  public BlockVector2 divide(int x, int z) {
    return BlockVector2.at(this.x / x, this.z / z);
  }

  /**
   * Udfør skalar division og returner en ny vektor.
   *
   * @param n værdien der skal divideres med
   * @return en ny vektor
   */
  public BlockVector2 divide(int n) {
    return divide(n, n);
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
    return x * x + z * z;
  }

  /**
   * Finder afstanden mellem denne vektor og en anden vektor.
   *
   * @param other den anden vektor
   * @return afstanden
   */
  public double distance(BlockVector2 other) {
    return Math.sqrt(distanceSq(other));
  }

  /**
   * Finder afstanden mellem denne vektor og en anden vektor, kvadreret.
   *
   * @param other den anden vektor
   * @return afstanden, kvadreret
   */
  public int distanceSq(BlockVector2 other) {
    int dx = other.x - x;
    int dz = other.z - z;
    return dx * dx + dz * dz;
  }

  /**
   * Tjekker for om en vektor er indeholdt i en anden vektor.
   *
   * @param min det mindste punkt (X og Z er højeste)
   * @param max det største punkt (X og Z er højeste)
   * @return om vektoren er indeholdt
   */
  public boolean containedWithin(BlockVector2 min, BlockVector2 max) {
    return x >= min.x && x <= max.x
        && z >= min.z && z <= max.z;
  }

  /**
   * Finder minimumskomponenterne af to vektorer.
   *
   * @param v2 den anden vektor
   * @return minimum
   */
  public BlockVector2 getMinimum(BlockVector2 v2) {
    return new BlockVector2(
        Math.min(x, v2.x),
        Math.min(z, v2.z)
    );
  }

  /**
   * Finder maksimumskomponenterne af to vektorer.
   *
   * @param v2 den anden vektor
   * @return maximum
   */
  public BlockVector2 getMaximum(BlockVector2 v2) {
    return new BlockVector2(
        Math.max(x, v2.x),
        Math.max(z, v2.z)
    );
  }

  /**
   * Konverterer denne vektor til en 3D vektor ved at tilføje et Y-komponent.
   *
   * @return en ny vektor
   */
  public BlockVector3 toBlockVector3() {
    return toBlockVector3(0);
  }

  /**
   * Konverterer denne vektor til en 3D vektor ved at tilføje et specificeret Y-komponent.
   *
   * @param y Y-komponenten
   * @return en ny vektor
   */
  public BlockVector3 toBlockVector3(int y) {
    return BlockVector3.at(x, y, z);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + z + ")";
  }
}
