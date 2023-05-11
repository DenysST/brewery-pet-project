package brewery.inventory.service.repositories;

import brewery.inventory.service.domain.BeerInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 2019-01-26.
 */
public interface BeerInventoryRepository extends JpaRepository<BeerInventory, UUID> {

    List<BeerInventory> findAllByBeerId(UUID beerId);

    List<BeerInventory> findAllByUpc(String upc);
}
