package net.fmattaperdomo.model.role.gateways;

import net.fmattaperdomo.model.role.Role;
import net.fmattaperdomo.model.role.enums.TypeRole;
import reactor.core.publisher.Mono;

public interface RoleRepository {
    Mono<Role> getRoleByName(TypeRole typeRole);
}
