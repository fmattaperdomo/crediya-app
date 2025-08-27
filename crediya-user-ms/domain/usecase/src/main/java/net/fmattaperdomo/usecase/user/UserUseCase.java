package net.fmattaperdomo.usecase.user;

import lombok.RequiredArgsConstructor;
import net.fmattaperdomo.model.role.gateways.RoleRepository;
import net.fmattaperdomo.model.user.User;
import net.fmattaperdomo.model.user.gateways.LoggerRepository;
import net.fmattaperdomo.model.user.gateways.UserRepository;
import net.fmattaperdomo.usecase.exception.EmailAlreadyRegisteredException;
import net.fmattaperdomo.usecase.exception.UserNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository rolRepository;
    private final LoggerRepository log;

    public Mono<User> saveUser(User user) {
        String email = user.getEmail();
        return this.userRepository.existByEmail(email)
                .flatMap(emailExist -> {
                    if (Boolean.TRUE.equals(emailExist)) {
                        log.error("UUC: Email already registered {}", emailExist);
                        return Mono.error(new EmailAlreadyRegisteredException("Email already registered"));
                    }
                    return userRepository.saveUser(user)
                            .doOnSuccess(createdUser -> log.info("UUC: User created: {}", createdUser.getUserId()))
                            .doOnError(error -> log.error("UUC: Error creating user", error));
                });
    }

    public Flux<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Mono<User> getUserByUserId(Long userId) {
        log.info("UUC: Get User with id: {}", userId);
        return userRepository.getUserByUserId(userId);
    }

    public Mono<User> updateUser(Long userId, User user) {
        log.info("UUC: Updating User with id: {}", userId);
        return this.userRepository
                .findById(userId)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not found")))
                .flatMap(updatedUser -> {
                    return userRepository.updateUser(userId, user)
                            .doOnSuccess(updateUser -> log.info("UUC: User updated: {}", updateUser.getUserId()))
                            .doOnError(error -> log.error("UUC: Error creating user", error));
                });
    }

    public Mono<Void> deleteById(Long userId) {
        log.info("UUC : Deleting User with id: {}", userId);
        return this.userRepository
                .findById(userId)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not found")))
                .flatMap(deletedUser -> {
                    return userRepository.deleteById(userId)
                            .doOnSuccess(deleteUser -> log.info("UUC: User deleted: {}", deletedUser.getUserId()))
                            .doOnError(error -> log.error("UUC: Error delete user", error));
                });
    }
}