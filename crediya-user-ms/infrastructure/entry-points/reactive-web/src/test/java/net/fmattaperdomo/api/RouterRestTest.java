package net.fmattaperdomo.api;

import net.fmattaperdomo.api.config.UserPath;
import net.fmattaperdomo.model.user.User;
import net.fmattaperdomo.usecase.user.UserUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@EnableConfigurationProperties(UserPath.class)
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserUseCase userUseCase;

    private final String users = "/api/v1/users";
    private final String usersById = "/api/v1/users";

    private final User userOne = User.builder()
            .userId(1L)
            .firstname("Eduardo")
            .lastname("Matta Perdomo")
            .birthdate(LocalDate.of(2022,8,26))
            .email("eduardo@gmail.com")
            .password("123456")
            .nid("11323602")
            .address("Av 123 Sw Bogotá 111156")
            .phone("3002183755")
            .baseSalary(BigDecimal.valueOf(5500000))
            .roleId(1)
            .build();

    private final User userTwo = User.builder()
            .userId(2L)
            .firstname("Juan")
            .lastname("Matta Perdomo")
            .birthdate(LocalDate.of(2022,8,26))
            .email("Juan@gmail.com")
            .password("123456")
            .nid("11323602")
            .address("Av 123 Sw Bogotá 111156")
            .phone("3002183756")
            .baseSalary(BigDecimal.valueOf(7500000))
            .roleId(1)
            .build();

    @Autowired
    private UserPath userPath;

    @Test
    void shouldLoadUserPathProperties() {
        assertEquals("/api/v1/users", userPath.getUsers());
        assertEquals("/api/v1/users/{id}", userPath.getUsersById());
    }

    @Test
    void shouldGetAllUsers() {
        when(userUseCase.getAllUsers()).thenReturn(Flux.just(userOne, userTwo));

        webTestClient.get()
                .uri(users)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class)
                .hasSize(2)
                .value(users -> {
                    Assertions.assertThat(users).isNotEmpty();
                    Assertions.assertThat(users.getFirst().getUserId()).isEqualTo(1L);
                });

    }

    @Test
    void shouldGetUserById() {
        Long userId = 1L;

        when(userUseCase.getUserByUserId(userId).thenReturn(Mono.just(userOne)));

        webTestClient.get()
                .uri(users + "/" + userId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(response -> Assertions.assertThat(response.getUserId()).isEqualTo(userId));
    }

    @Test
    void shouldPostSaveUser() {

        when(userUseCase.saveUser(any(User.class))).thenReturn(Mono.just(userOne));

        webTestClient.post()
                .uri(users)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userOne)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(saved -> Assertions.assertThat(saved.getNid()).isEqualTo(userOne.getNid()));
    }

    @Test
    void shouldPutUpdateUser() {
        User user = User.builder()
            .userId(1L)
            .firstname("Eduardo")
            .lastname("Matta Perdomo")
            .birthdate(LocalDate.of(2022,8,26))
            .email("eduardo@gmail.com")
            .password("123456")
            .nid("11323602")
            .address("Av 123 Sw Bogotá 111156")
            .phone("3002183755")
            .baseSalary(BigDecimal.valueOf(5500000))
            .roleId(1)
            .build();

        when(userUseCase.updateUser(1L,any(User.class))).thenReturn(Mono.just(user));

        webTestClient.put()
                .uri(users)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(updated -> Assertions.assertThat(updated.getNid()).isNotEmpty());
    }

    @Test
    void shouldDeleteUser() {
        Long userId = 1L;
        when(userUseCase.deleteById(userId)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri(users + "/" + userId)
                .exchange()
                .expectStatus().isNoContent();
    }
}
