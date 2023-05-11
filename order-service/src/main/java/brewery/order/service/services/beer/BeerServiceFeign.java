package brewery.order.service.services.beer;

import brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Profile("local-discovery")
@Service
@RequiredArgsConstructor
public class BeerServiceFeign implements BeerService {
    private final BeerServiceFeignClient feignClient;

    @Override
    public Optional<BeerDto> getBeerById(UUID uuid) {
        return feignClient.getBeerById(uuid.toString());
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        return feignClient.getBeerByUpc(upc);
    }
}