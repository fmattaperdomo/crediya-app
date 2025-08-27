package net.fmattaperdomo.r2dbc.helper;


import lombok.RequiredArgsConstructor;
import net.fmattaperdomo.model.user.gateways.TransactionalWrapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransactionalWrapperAdapter implements TransactionalWrapper {
    private final TransactionalOperator transactionalOperator;

    @Override
    public <T> Mono<T> transactional(Mono<T> publisher) {
        return null;
    }
}
