package brewery.gateway.config;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class AuthenticationPrefilter extends AbstractGatewayFilterFactory<AuthenticationPrefilter.Config> {
    public static final String AUTH_HEADER = "Authorization";

    private final WebClient.Builder webClientBuilder;

    public AuthenticationPrefilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder=webClientBuilder;
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String bearerToken = request.getHeaders().getFirst(AUTH_HEADER);
                return webClientBuilder.build().get()
                        .uri("http://localhost:9091/api/v1/validate")
                        .header(AUTH_HEADER, bearerToken)
                        .retrieve().bodyToMono(Boolean.class)
                        .map(response -> {
                            log.info("validation status: " + response);
                            return exchange;
                        }).flatMap(chain::filter).onErrorResume(error -> {
                            if (error.getMessage().contains("403")) {
                                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            } else {
                                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                            return exchange.getResponse().setComplete();
                        });
        };
    }


    @NoArgsConstructor
    public static class Config {


    }
}
