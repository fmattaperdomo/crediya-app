package net.fmattaperdomo.model.application.gateways;

import net.fmattaperdomo.model.application.Application;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApplicationRepository {
    Mono<Application> findById(Long applicationId);
    Mono<Application> saveApplication(Application application);
    Flux<Application> getAllApplications();
    Mono<Application> getApplicationByApplicationId(Long applicationId);
    Mono<Application> updateApplication(Application application);
    Mono<Void> deleteById(Long applicationId);
}
