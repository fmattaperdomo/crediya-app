package net.fmattaperdomo.r2dbc;

import net.fmattaperdomo.model.application.Application;
import net.fmattaperdomo.model.application.gateways.ApplicationRepository;
import net.fmattaperdomo.r2dbc.entity.ApplicationEntity;
import net.fmattaperdomo.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ApplicationReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Application,
        ApplicationEntity,
        Long,
        ApplicationReactiveRepository
> implements ApplicationRepository {
    public ApplicationReactiveRepositoryAdapter(ApplicationReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Application.class));
    }

    @Override
    public Mono<Application> saveApplication(Application application) {
        return super.save(application);
    }

    @Override
    public Flux<Application> getAllApplications() {
        return super.findAll();
    }

    @Override
    public Mono<Application> getApplicationByApplicationId(Long applicationId) {
        return super.findById(applicationId);
    }

    @Override
    public Mono<Application> updateApplication(Long appId, Application application) {
        return super.save(application);
    }

    @Override
    public Mono<Void> deleteById(Long applicationId) {
        return super.repository.deleteById(applicationId);
    }
}
