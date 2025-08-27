package net.fmattaperdomo.api.config;

import net.fmattaperdomo.api.Handler;
import net.fmattaperdomo.api.RouterRest;
import net.fmattaperdomo.model.user.User;
import net.fmattaperdomo.usecase.user.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
@Import({CorsConfig.class, SecurityHeadersConfig.class})
class ConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserUseCase userUseCase;

    private final User userOne = User.builder()
            .userId(1L)
            .firstname("Eduardo")
            .lastname("Matta Perdomo")
            .birthdate(LocalDate.of(2022,8,26))
            .email("eduardo@gmail.com")
            .password("123456")
            .nid("11323602")
            .address(new Address("Av 123 Sw","Bogotá", "111156"))
            .phone("3002183755")
            .baseSalary(BigDecimal.valueOf(5500000))
            .role(new Role(1,"Admin","Admin"))
            .build();

    private final User userTwo = User.builder()
            .userId(2L)
            .firstname("Juan")
            .lastname("Matta Perdomo")
            .birthdate(LocalDate.of(2022,8,26))
            .email("Juan@gmail.com")
            .password("123456")
            .nid("11323602")
            .address(new Address("Av 123 Sw","Bogotá", "111156"))
            .phone("3002183756")
            .baseSalary(BigDecimal.valueOf(7500000))
            .role(new Role(1,"Admin","Admin"))
            .build();

    @BeforeEach
    void setUp() {
        when(userUseCase.getAllUsers()).thenReturn(Flux.just(userOne, userTwo));
    }

    @Test
    void corsConfigurationShouldAllowOrigins() {
        webTestClient.get()
                .uri("/api/v1/users")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Security-Policy",
                        "default-src 'self'; frame-ancestors 'self'; form-action 'self'")
                .expectHeader().valueEquals("Strict-Transport-Security", "max-age=31536000;")
                .expectHeader().valueEquals("X-Content-Type-Options", "nosniff")
                .expectHeader().valueEquals("Server", "")
                .expectHeader().valueEquals("Cache-Control", "no-store")
                .expectHeader().valueEquals("Pragma", "no-cache")
                .expectHeader().valueEquals("Referrer-Policy", "strict-origin-when-cross-origin");
    }
}