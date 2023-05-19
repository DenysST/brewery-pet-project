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
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.IPA.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_1_UPC)
                .build();

        Beer b2 = Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_2_UPC)
                .build();

        Beer b3 = Beer.builder()
                .beerName("Pinball Porter")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_3_UPC)
                .build();
        repository.save(b1);
        repository.save(b2);
        repository.save(b3);

    }
}
