package brewery.order.service.services.beer;

import brewery.model.BeerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "beer-service")
public interface BeerServiceFeignClient {
    @RequestMapping(method = RequestMethod.GET,  value = BeerServiceRestTemplate.BEER_PATH_V1 + "{beerId}")
    Optional<BeerDto> getBeerById(@PathVariable String beerId);

    @RequestMapping(method = RequestMethod.GET,  value = BeerServiceRestTemplate.BEER_UPC_PATH_V1 + "{upc}")
    Optional<BeerDto> getBeerByUpc(@PathVariable String upc);
}