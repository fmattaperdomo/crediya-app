package net.fmattaperdomo.r2dbc;

import net.fmattaperdomo.model.user.User;
import net.fmattaperdomo.model.user.gateways.UserRepository;
import net.fmattaperdomo.r2dbc.entity.UserEntity;
import net.fmattaperdomo.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<
    User,
    UserEntity,
    Long,
    UserReactiveRepository
> implements UserRepository {
    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }
    @Override
    public Mono<Boolean> existByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Mono<User> saveUser(User user) {
        return super.save(user);
    }

    @Override
    public Flux<User> getAllUsers() {
        return super.findAll();
    }

    @Override
    public Mono<User> getUserByUserId(Long userId) {
        return super.findById(userId);
    }

    @Override
    public Mono<User> updateUser(Long userId, User user) {
        return super.save(user);
    }

    @Override
    public Mono<Void> deleteById(Long userId) {
        return repository.deleteById(userId);
    }
}
