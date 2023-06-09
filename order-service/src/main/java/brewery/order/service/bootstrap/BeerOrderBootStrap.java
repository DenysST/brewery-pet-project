package brewery.order.service.bootstrap;

import brewery.order.service.domain.Customer;
import brewery.order.service.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderBootStrap {
    public static final String TASTING_ROOM = "Tasting Room";
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Customer savedCustomer = repository.save(Customer.builder()
                        .customerName(TASTING_ROOM)
                        .apiKey(UUID.randomUUID())
                        .build());

                log.debug("Tasting Room Customer Id: " + savedCustomer.getId().toString());
            }
        };
    }
}
