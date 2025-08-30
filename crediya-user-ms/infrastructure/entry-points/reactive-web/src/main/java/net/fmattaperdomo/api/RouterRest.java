package net.fmattaperdomo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.fmattaperdomo.api.config.UserPath;
import net.fmattaperdomo.api.dto.response.UserResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
@Tag(name = "User", description = "This is a catalog for users")
public class RouterRest {
    private final UserPath userPath;
    private final Handler userHandler;

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/users",
                    beanClass = Handler.class,
                    beanMethod = "getAllRestaurants",
                    operation = @Operation(
                            operationId = "getAllUsers",
                            summary = "List all users",
                            parameters = {

                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "List of users",
                                            content = @Content(schema = @Schema(implementation = UserResponse.class)))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/users/{userId}",
                    beanClass = Handler.class,
                    beanMethod = "getUserByUserId",
                    operation = @Operation(
                            operationId = "getUserByUserId",
                            summary = "user by Id",
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "userId", description = "User By Id")
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "List of users",
                                            content = @Content(schema = @Schema(implementation = UserResponse.class)))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/users/{userId}",
                    beanClass = Handler.class,
                    beanMethod = "deleteById",
                    operation = @Operation(
                            operationId = "deleteById",
                            summary = "delete user",
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "userId", description = "User By Id")
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "List of users",
                                            content = @Content(schema = @Schema(implementation = UserResponse.class)))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/users/{userId}",
                    beanClass = Handler.class,
                    beanMethod = "updateUser",
                    operation = @Operation(
                            operationId = "updateUser",
                            summary = "update user",
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "userId", description = "User By Id"),
                                    @Parameter(in = ParameterIn.PATH, name = "user", description = "User object")
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "List of users",
                                            content = @Content(schema = @Schema(implementation = UserResponse.class)))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/users",
                    beanClass = Handler.class,
                    beanMethod = "saveUser",
                    operation = @Operation(
                            operationId = "saveUser",
                            summary = "save user",
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "user", description = "User object")
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "List of users",
                                            content = @Content(schema = @Schema(implementation = UserResponse.class)))
                            }
                    )
            )
    })

    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST(userPath.getUsers()), userHandler::listenSaveUser)
                .andRoute(PUT(userPath.getUsers()), userHandler::listenUpdateUser)
                .andRoute(DELETE(userPath.getUsersById()), userHandler::listenDeleteUser)
                .andRoute(GET(userPath.getUsers()), userHandler::listenGetAllUsers)
                .andRoute(GET(userPath.getUsersById()), userHandler::listenGetUserById);
    }
}
