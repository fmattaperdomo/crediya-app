package net.fmattaperdomo.usecase.typeloan;

import lombok.RequiredArgsConstructor;
import net.fmattaperdomo.model.application.gateways.LoggerRepository;
import net.fmattaperdomo.model.typeloan.TypeLoan;
import net.fmattaperdomo.model.typeloan.gateways.TypeLoanRepository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TypeLoanUseCase {

    private final TypeLoanRepository typeLoanRepository;

    private final LoggerRepository log;

    public Mono<TypeLoan> findById(Integer typeLoanId) {
        return typeLoanRepository.findById(typeLoanId);
    }
}
