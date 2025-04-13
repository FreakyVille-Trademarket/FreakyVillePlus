package dk.fvtrademarket.fvplus.api.service.region;

import dk.fvtrademarket.fvplus.api.enums.FreakyVilleServer;
import dk.fvtrademarket.fvplus.api.enums.PrisonSector;
import dk.fvtrademarket.fvplus.api.region.Region;
import dk.fvtrademarket.fvplus.api.service.Service;
import dk.fvtrademarket.fvplus.api.util.BlockVector3;
import java.util.Collection;

public interface RegionService extends Service {
  void addRegion(Region region);

  void removeRegion(Region region);

  Collection<Region> getRegions();

  Collection<Region> getRegionsByServer(FreakyVilleServer server);

  Collection<Region> getRegionsByPrisonSector(PrisonSector prisonSector);

  Collection<Region> getRegionsAtClientLocation(BlockVector3 location);
}
