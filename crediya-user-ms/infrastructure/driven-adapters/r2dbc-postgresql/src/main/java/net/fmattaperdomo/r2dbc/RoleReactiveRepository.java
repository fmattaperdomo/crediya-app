package net.fmattaperdomo.r2dbc;

import net.fmattaperdomo.model.role.enums.TypeRole;
import net.fmattaperdomo.r2dbc.entity.RoleEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface RoleReactiveRepository extends ReactiveCrudRepository<RoleEntity, Integer>, ReactiveQueryByExampleExecutor<RoleEntity> {
    @Query("SELECT * FROM role WHERE name = :name")
    Mono<RoleEntity> findByRoleTypeName(TypeRole name);
}
