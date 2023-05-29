package brewery.beerservice.bootstrap;

import brewery.beerservice.domain.Beer;
import brewery.beerservice.repositories.BeerRepository;
import brewery.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@RequiredArgsConstructor
@Component
public class BeerLoader {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    @Bean
    CommandLineRunner commandLineRunner(BeerRepository repository) {
        return args -> {
            List<Beer> allBeers = repository.findAll();
            if (allBeers.size() == 0) {
                loadBeerObjects(repository);
            }
        };
    }

    private void loadBeerObjects(BeerRepository repository) {
        Beer b1 = Beer.builder()
                .beerName("Heineken")
                .beerStyle(BeerStyleEnum.LAGER.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("3.99"))
                .upc(BEER_1_UPC)
                .build();

        Beer b2 = Beer.builder()
                .beerName("Budweiser")
                .beerStyle(BeerStyleEnum.LAGER.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("3.95"))
                .upc(BEER_2_UPC)
                .build();

        Beer b3 = Beer.builder()
                .beerName("Pinball Porter")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("5.99"))
                .upc(BEER_3_UPC)
                .build();

        Beer b4 = Beer.builder()
                .beerName("Guinness")
                .beerStyle(BeerStyleEnum.STOUT.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("6.99"))
                .upc("0083783375214")
                .build();
        Beer b5 = Beer.builder()
                .beerName("Corona")
                .beerStyle(BeerStyleEnum.LAGER.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("4.50"))
                .upc("0083783375215")
                .build();
        Beer b6 = Beer.builder()
                .beerName("Carlsberg")
                .beerStyle(BeerStyleEnum.LAGER.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("3.99"))
                .upc("0083783375216")
                .build();
        Beer b7 = Beer.builder()
                .beerName("Lvivske Rizdviane")
                .beerStyle(BeerStyleEnum.STOUT.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("2.99"))
                .upc("00837833752137")
                .build();
        Beer b8 = Beer.builder()
                .beerName("Chernihivske")
                .beerStyle(BeerStyleEnum.LAGER.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("2.99"))
                .upc("0083783375218")
                .build();
        repository.save(b1);
        repository.save(b2);
        repository.save(b3);
        repository.save(b4);
        repository.save(b5);
        repository.save(b6);
        repository.save(b7);
        repository.save(b8);

    }
}
