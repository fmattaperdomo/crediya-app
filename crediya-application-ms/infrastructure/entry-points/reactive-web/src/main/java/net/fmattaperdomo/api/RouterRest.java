package net.fmattaperdomo.api;

import lombok.RequiredArgsConstructor;
import net.fmattaperdomo.api.config.ApplicationPath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RouterRest {
    private final ApplicationPath applicationPath;
    private final Handler applicationHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST(applicationPath.getApplication()), applicationHandler::listenSaveApplication)
                .andRoute(PUT(applicationPath.getApplication()), applicationHandler::listenUpdateApplication)
                .andRoute(DELETE(applicationPath.getApplicationById()), applicationHandler::listenDeleteApplication)
                .andRoute(GET(applicationPath.getApplication()), applicationHandler::listenGetAllApplications)
                .andRoute(GET(applicationPath.getApplicationById()), applicationHandler::listenGetApplicationById);
    }}
