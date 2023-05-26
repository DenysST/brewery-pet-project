package brewery.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.codec.ServerCodecConfigurer;


@Profile("local-discovery")
@EnableDiscoveryClient
@Configuration
@AllArgsConstructor
public class LoadBalancedRoutesConfig {

    private final AuthenticationPrefilter authFilter;

    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("beer-service", r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                        .filters(f ->
                                f.filter(authFilter.apply(
                                                new AuthenticationPrefilter.Config())))
                        .uri("lb://beer-service"))
                .route("order-service", r -> r.path("/api/v1/customers/**")
                        .filters(f ->
                                f.filter(authFilter.apply(
                                        new AuthenticationPrefilter.Config())))
                        .uri("lb://order-service"))
                .route("inventory-service", r -> r.path("/api/v1/beer/*/inventory")
                        .filters(f ->
                                f.filter(authFilter.apply(
                                        new AuthenticationPrefilter.Config())))
                        .uri("lb://inventory-service"))
                .route("security-service", r -> r.path("/api/v1/auth/*", "/api/v1/validate*")
                        .uri("lb://security-service"))
                .build();
    }
}
