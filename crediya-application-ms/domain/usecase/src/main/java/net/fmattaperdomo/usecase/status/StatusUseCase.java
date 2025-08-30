package net.fmattaperdomo.usecase.status;

import lombok.RequiredArgsConstructor;

import net.fmattaperdomo.model.application.gateways.LoggerRepository;
import net.fmattaperdomo.model.status.Status;
import net.fmattaperdomo.model.status.gateways.StatusRepository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StatusUseCase {
    private final StatusRepository statusRepository;

    private final LoggerRepository log;

    public Mono<Status> findById(Integer statusId) {
        return statusRepository.findById(statusId);
    }
}


