package net.fmattaperdomo.api.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fmattaperdomo.api.dto.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserActionsClientImpl implements UserActionsClient {
    private final WebClient webClient;

    @Override
    public Mono<UserResponse> findByNid(String nid) {
        log.info("UAC: Reading user with nid: {}", nid);
        return this.webClient
                .get()
                .uri(RESOURCE + "/{nid}", nid)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, r ->MONO_400_ERROR)
                .bodyToMono(UserResponse.class)
                .doOnSuccess(res -> log.info("User read: {}", res))
                .doOnError(e -> log.error("Error reading user with nid: {}",nid, e));
    }

    private static final String RESOURCE = "/users";
    private static final String ERROR_MSG_4XX_ = "Error while creating user";
    private static final String ERROR_MSG_5XX =  "Error while calling user service";
    private static final Mono<Throwable> MONO_400_ERROR =Mono.error(new IllegalArgumentException(ERROR_MSG_4XX_));
    private static final Mono<Throwable> MONO_500_ERROR = Mono.error(new IllegalArgumentException(ERROR_MSG_5XX));
}
