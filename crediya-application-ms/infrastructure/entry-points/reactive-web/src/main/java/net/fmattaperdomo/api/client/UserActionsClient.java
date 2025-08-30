package net.fmattaperdomo.api.client;

import net.fmattaperdomo.api.dto.response.UserResponse;
import reactor.core.publisher.Mono;

public interface UserActionsClient {
    Mono<UserResponse> findByNid(String nid);
}
