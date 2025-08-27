package net.fmattaperdomo.r2dbc;

import net.fmattaperdomo.model.user.User;
import net.fmattaperdomo.r2dbc.entity.RoleEntity;
import net.fmattaperdomo.r2dbc.entity.UserEntity;
import net.fmattaperdomo.r2dbc.record.AddressRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserReactiveRepositoryAdapterTest {
    @InjectMocks
    UserReactiveRepositoryAdapter repositoryAdapter;

    @Mock
    UserReactiveRepository repository;

    @Mock
    ObjectMapper mapper;

    private final UserEntity userEntity = UserEntity.builder()
            .userId(1L)
            .firstname("Francisco")
            .lastname("Matta Perdomo")
            .birthdate(LocalDate.of(2025,8,26))
            .email("fmattaperdomo@gmail.com")
            .password("123456")
            .nid("11323600")
            .address(new AddressRecord("Av 123 Sw","Bogotá", "111156"))
            .phone("3002183755")
            .baseSalary(BigDecimal.valueOf(4500000))
            .role(new RoleEntity(1,"Admin","Admin"))
            .build();

    private final User user = User.builder()
            .userId(1L)
            .firstname("Eduardo")
            .lastname("Matta Perdomo")
            .birthdate(LocalDate.of(2022,8,26))
            .email("eduardo@gmail.com")
            .password("123456")
            .nid("11323602")
            .address(new Address("Av 123 Sw","Bogotá", "111156"))
            .phone("3002183755")
            .baseSalary(BigDecimal.valueOf(5500000))
            .role(new Role(1,"Admin","Admin"))
            .build();

    private final User otherUser = User.builder()
            .userId(1L)
            .firstname("Juan")
            .lastname("Matta Perdomo")
            .birthdate(LocalDate.of(2000,8,26))
            .email("juan@gmail.com")
            .password("123456")
            .nid("11323601")
            .address(new Address("Av 123 Sw","Bogotá", "111156"))
            .phone("3002183755")
            .baseSalary(BigDecimal.valueOf(7500000))
            .role(new Role(1,"Admin","Admin"))
            .build();


    @Test
    void shouldFindUserById() {
        when(mapper.map(userEntity, User.class)).thenReturn(user);
        when(repository.findById(1L)).thenReturn(Mono.just(userEntity));
        Mono<User> result = repositoryAdapter.findById(1L);

        StepVerifier.create(result)
                .expectNextMatches(t -> t.getUserId().equals(1L) && t.getNid().equals("11323600"))
                .verifyComplete();
    }

    @Test
    void shouldFindAllUser() {
        when(mapper.map(userEntity, User.class)).thenReturn(user);
        when(repository.findAll()).thenReturn(Flux.just(userEntity));

        Flux<User> result = repositoryAdapter.findAll();

        StepVerifier.create(result)
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    void shouldSaveUser() {
        when(mapper.map(userEntity, User.class)).thenReturn(user);
        when(mapper.map(user, UserEntity.class)).thenReturn(userEntity);
        when(repository.save(userEntity)).thenReturn(Mono.just(userEntity));

        Mono<User> result = repositoryAdapter.save(user);

        StepVerifier.create(result)
                .expectNext(user)
                .verifyComplete();
    }
}
