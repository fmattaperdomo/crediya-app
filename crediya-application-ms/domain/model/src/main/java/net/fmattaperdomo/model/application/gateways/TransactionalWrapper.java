package net.fmattaperdomo.model.application.gateways;

import reactor.core.publisher.Mono;

public interface TransactionalWrapper {
    <T> Mono<T> transactional(Mono<T> publisher);
}
