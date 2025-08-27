package net.fmattaperdomo.model.typeloan.gateways;


import net.fmattaperdomo.model.typeloan.TypeLoan;
import reactor.core.publisher.Mono;

public interface TypeLoanRepository {
    Mono<TypeLoan> findById(Integer typeLoanId);
}
