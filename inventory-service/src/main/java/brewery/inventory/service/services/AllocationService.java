package brewery.inventory.service.services;

import brewery.model.BeerOrderDto;

public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);
}
