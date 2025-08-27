package net.fmattaperdomo.model.user.gateways;

import net.fmattaperdomo.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> findById(Long userId);
    Mono<Boolean> existByEmail(String email);
    Mono<User> saveUser(User user);
    Flux<User> getAllUsers();
    Mono<User> getUserByUserId(Long userId);
    Mono<User> updateUser(Long userId, User user);
    Mono<Void> deleteById(Long userId);
}
