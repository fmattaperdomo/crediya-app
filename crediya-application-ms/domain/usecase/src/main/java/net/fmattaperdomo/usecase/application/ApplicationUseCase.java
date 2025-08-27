package net.fmattaperdomo.usecase.application;

import lombok.RequiredArgsConstructor;
import net.fmattaperdomo.model.application.Application;
import net.fmattaperdomo.model.application.gateways.ApplicationRepository;
import net.fmattaperdomo.model.application.gateways.LoggerRepository;
import net.fmattaperdomo.model.status.gateways.StatusRepository;
import net.fmattaperdomo.model.typeloan.gateways.TypeLoanRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ApplicationUseCase {
    private final ApplicationRepository applicationRepository;
    private final StatusRepository statusRepository;
    private final TypeLoanRepository typeLoanRepository;
    private final LoggerRepository log;

    public Mono<Application> saveApplication(Application application) {
        return applicationRepository.saveApplication(application);
    }

    public Flux<Application> getAllApplications() {
        return applicationRepository.getAllApplications();
    }

    public Mono<Application> getApplicationByApplicationId(Long applicationId) {
        return applicationRepository.getApplicationByApplicationId(applicationId);
    }

    public Mono<Application> updateApplication(Application application) {
        return applicationRepository.updateApplication(application);
    }

    public Mono<Void> deleteById(Long applicationId) {
        return applicationRepository.deleteById(applicationId);
    }
}
