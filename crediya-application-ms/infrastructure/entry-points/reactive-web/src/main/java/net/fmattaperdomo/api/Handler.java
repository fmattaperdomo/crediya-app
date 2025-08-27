package net.fmattaperdomo.api;

import lombok.RequiredArgsConstructor;
import net.fmattaperdomo.model.application.Application;
import net.fmattaperdomo.usecase.application.ApplicationUseCase;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final ApplicationUseCase applicationUseCase;

    public Mono<ServerResponse> listenSaveApplication(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Application.class)
                .flatMap(applicationUseCase::saveApplication)
                .flatMap(savedApplication -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedApplication));
    }

    public Mono<ServerResponse> listenUpdateApplication(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Application.class)
                .flatMap(applicationUseCase::updateApplication)
                .flatMap(savedApplication -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedApplication));
    }

    public Mono<ServerResponse> listenGetAllApplications(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(applicationUseCase.getAllApplications(), Application.class);
    }

    public Mono<ServerResponse> listenGetApplicationById(ServerRequest serverRequest) {
        Long applicationId = Long.valueOf(serverRequest.pathVariable("applicationId"));

        return applicationUseCase.getApplicationByApplicationId(applicationId)
                .flatMap(application -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(application))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> listenDeleteApplication(ServerRequest serverRequest) {
        Long applicationId = Long.valueOf(serverRequest.pathVariable("applicationId"));

        return applicationUseCase.deleteById(applicationId)
                .then(ServerResponse.noContent().build());
    }
}
