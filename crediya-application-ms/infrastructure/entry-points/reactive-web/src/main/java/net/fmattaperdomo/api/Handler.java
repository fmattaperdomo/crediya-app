package net.fmattaperdomo.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fmattaperdomo.api.client.UserActionsClient;
import net.fmattaperdomo.model.application.Application;
import net.fmattaperdomo.usecase.application.ApplicationUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Component
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Applications", description = "API to management application")
public class Handler {
    private final ApplicationUseCase applicationUseCase;
    private final UserActionsClient userActionsClient;

    public Mono<ServerResponse> listenSaveApplication(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Application.class)
                .flatMap(applicationUseCase::saveApplication)
                .flatMap(savedApplication -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedApplication))
                    .onErrorResume(error -> {
                        log.error("Error processing the request", error);
                        return ServerResponse.status(500).build();
                    });
    }

    public Mono<ServerResponse> listenUpdateApplication(ServerRequest serverRequest) {
        var appId =serverRequest.pathVariable("applicationId");

        if (Objects.isNull(appId)) {
            return ServerResponse.badRequest().bodyValue("Application>Id is required");
        }
        return serverRequest.bodyToMono(Application.class)
                .flatMap(appReq -> applicationUseCase.updateApplication(Long.valueOf(appId),appReq))
                .flatMap(savedApplication -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedApplication))
               .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> listenGetAllApplications(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(applicationUseCase.getAllApplications(), Application.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> listenGetApplicationById(ServerRequest serverRequest) {
        var appId = serverRequest.pathVariable("applicationId");

        if (Objects.isNull(appId)) {
            return ServerResponse.badRequest().bodyValue("Application>Id is required");
        }
        return applicationUseCase.getApplicationByApplicationId(Long.valueOf(appId))
                .flatMap(application -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(application))
                    .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> listenDeleteApplication(ServerRequest serverRequest) {
        var appId =serverRequest.pathVariable("applicationId");

        if (Objects.isNull(appId)) {
            return ServerResponse.badRequest().bodyValue("Application>Id is required");
        }
        return applicationUseCase.deleteById(Long.valueOf(appId))
                .then(ServerResponse.noContent().build());
    }
}
