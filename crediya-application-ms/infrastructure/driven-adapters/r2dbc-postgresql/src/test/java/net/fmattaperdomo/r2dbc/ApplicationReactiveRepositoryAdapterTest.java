package net.fmattaperdomo.r2dbc;

import net.fmattaperdomo.model.application.Application;
import net.fmattaperdomo.r2dbc.entity.ApplicationEntity;
import net.fmattaperdomo.r2dbc.entity.StatusEntity;
import net.fmattaperdomo.r2dbc.entity.TypeLoanEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationReactiveRepositoryAdapterTest {
    @InjectMocks
    ApplicationReactiveRepositoryAdapter repositoryAdapter;

    @Mock
    ApplicationReactiveRepository repository;

    @Mock
    ObjectMapper mapper;

    private final ApplicationEntity applicationEntity = ApplicationEntity.builder()
            .applicationId(1L)
            .amount(BigDecimal.valueOf(4500000))
            .deadline(10)
            .email("fmattaperdomo@gmail.com")
            .statusId(new StatusEntity(1,"Approved","Approved"))
            .typeLoanId(new TypeLoanEntity(1,"Tipo hipotecario",BigDecimal.valueOf(1),BigDecimal.valueOf(1000000),BigDecimal.valueOf(12.5),true))
            .build();

    private final Application application = Application.builder()
            .applicationId(1L)
            .amount(BigDecimal.valueOf(4500000))
            .deadline(10)
            .email("fmattaperdomo@gmail.com")
            .statusId(new Status(1,"Approved","Approved"))
            .typeLoanId(new TypeLoan(1,"Tipo hipotecario",BigDecimal.valueOf(1),BigDecimal.valueOf(1000000),BigDecimal.valueOf(12.5),true))
            .build();

    private final Application otherApplication = Application.builder()
            .applicationId(2L)
            .amount(BigDecimal.valueOf(2500000))
            .deadline(12)
            .email("fmattaperdomo@gmail.com")
            .statusId(new Status(1,"Approved","Approved"))
            .typeLoanId(new TypeLoan(1,"Tipo hipotecario",BigDecimal.valueOf(1),BigDecimal.valueOf(1000000),BigDecimal.valueOf(12.5),true))
            .build();

    @Test
    void shouldFindApplicationById() {
        when(mapper.map(applicationEntity, Application.class)).thenReturn(application);
        when(repository.findById(1L)).thenReturn(Mono.just(applicationEntity));
        Mono<Application> result = repositoryAdapter.findById(1L);

        StepVerifier.create(result)
                .expectNextMatches(t -> t.getApplicationId().equals(1L) && t.getEmail().equals("fmattaperdomo@gmail.com"))
                .verifyComplete();
    }

    @Test
    void shouldFindAllApplication() {
        when(mapper.map(applicationEntity, Application.class)).thenReturn(application);
        when(repository.findAll()).thenReturn(Flux.just(applicationEntity));

        Flux<Application> result = repositoryAdapter.findAll();

        StepVerifier.create(result)
                .expectNext(application)
                .verifyComplete();
    }

    @Test
    void shouldSaveApplication() {
        when(mapper.map(applicationEntity, Application.class)).thenReturn(application);
        when(mapper.map(application, ApplicationEntity.class)).thenReturn(applicationEntity);
        when(repository.save(applicationEntity)).thenReturn(Mono.just(applicationEntity));

        Mono<Application> result = repositoryAdapter.save(application);

        StepVerifier.create(result)
                .expectNext(application)
                .verifyComplete();
    }
}
