package brewery.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BeerOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeerOrderServiceApplication.class, args);
    }
}
