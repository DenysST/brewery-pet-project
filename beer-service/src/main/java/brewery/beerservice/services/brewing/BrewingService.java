package brewery.beerservice.services.brewing;

import brewery.beerservice.web.mappers.BeerMapper;
import brewery.model.events.BrewBeerEvent;
import brewery.beerservice.config.RabbitConfig;
import brewery.beerservice.domain.Beer;
import brewery.beerservice.repositories.BeerRepository;
import brewery.beerservice.services.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final RabbitTemplate rabbitTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 10000) //every 5 seconds
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());

            log.debug("Min Onhand is: " + beer.getMinOnHand());
            log.debug("Inventory is: "  + invQOH);

            if(beer.getMinOnHand() >= invQOH){
                rabbitTemplate.convertAndSend(RabbitConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });

    }
}
