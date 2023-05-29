package com.example.securityservice.bootstrap;

import com.example.securityservice.services.AuthenticationService;
import com.example.securityservice.user.UserRepository;
import com.example.securityservice.web.models.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SecurityBootStrap {

    @Bean
    CommandLineRunner commandLineRunner(AuthenticationService service, UserRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                RegisterRequest registerRequest = RegisterRequest.builder()
                        .firstname("Test")
                        .lastname("Test")
                        .email("test@gmail.com")
                        .password("1234")
                        .build();
                service.register(registerRequest);
            }
        };
    }
}
