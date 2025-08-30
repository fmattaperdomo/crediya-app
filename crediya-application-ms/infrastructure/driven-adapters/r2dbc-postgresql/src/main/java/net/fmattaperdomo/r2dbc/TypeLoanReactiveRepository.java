package net.fmattaperdomo.r2dbc;

import net.fmattaperdomo.r2dbc.entity.TypeLoanEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TypeLoanReactiveRepository extends ReactiveCrudRepository<TypeLoanEntity, Integer>, ReactiveQueryByExampleExecutor<TypeLoanEntity> {

}
