package net.fmattaperdomo.api.config;

import net.fmattaperdomo.api.Handler;
import net.fmattaperdomo.api.RouterRest;
import net.fmattaperdomo.model.application.Application;
import net.fmattaperdomo.usecase.application.ApplicationUseCase;
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

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
@Import({CorsConfig.class, SecurityHeadersConfig.class})
class ConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private ApplicationUseCase applicationUseCase;

    private final Application applicationOne = Application.builder()
            .applicationId(1L)
            .amount(BigDecimal.valueOf(4500000))
            .deadline(10)
            .email("fmattaperdomo@gmail.com")
            .statusId(new Status(1,"Approved","Approved"))
            .typeLoanId(new TypeLoan(1,"Tipo hipotecario",BigDecimal.valueOf(1),BigDecimal.valueOf(1000000),BigDecimal.valueOf(12.5),true))
            .build();

    private final Application applicationTwo = Application.builder()
            .applicationId(2L)
            .amount(BigDecimal.valueOf(2500000))
            .deadline(12)
            .email("fmattaperdomo@gmail.com")
            .statusId(new Status(1,"Approved","Approved"))
            .typeLoanId(new TypeLoan(1,"Tipo hipotecario",BigDecimal.valueOf(1),BigDecimal.valueOf(1000000),BigDecimal.valueOf(12.5),true))
            .build();

    @BeforeEach
    void setUp() {
        when(applicationUseCase.getAllApplications()).thenReturn(Flux.just(applicationOne, applicationTwo));
    }

    @Test
    void corsConfigurationShouldAllowOrigins() {
        webTestClient.get()
                .uri("/api/v1/applications")
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