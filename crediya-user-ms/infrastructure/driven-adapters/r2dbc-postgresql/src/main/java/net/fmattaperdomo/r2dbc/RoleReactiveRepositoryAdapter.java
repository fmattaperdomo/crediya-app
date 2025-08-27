package net.fmattaperdomo.r2dbc;

import net.fmattaperdomo.model.role.Role;
import net.fmattaperdomo.model.role.enums.TypeRole;
import net.fmattaperdomo.model.role.gateways.RoleRepository;
import net.fmattaperdomo.model.user.User;
import net.fmattaperdomo.r2dbc.entity.RoleEntity;
import net.fmattaperdomo.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class RoleReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Role,
        RoleEntity,
        Integer,
        RoleReactiveRepository
> implements RoleRepository {
    public RoleReactiveRepositoryAdapter(RoleReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Role.class));
    }

    @Override
    public Mono<Role> getRoleByName(TypeRole typeRole) {
        return repository.findByRoleTypeName(typeRole)
                .map(super::toEntity);
    }
}
