package net.fmattaperdomo.model.status.gateways;

import net.fmattaperdomo.model.application.Application;
import net.fmattaperdomo.model.status.Status;
import reactor.core.publisher.Mono;

public interface StatusRepository {
    Mono<Status> findById(Integer statusId);
}
