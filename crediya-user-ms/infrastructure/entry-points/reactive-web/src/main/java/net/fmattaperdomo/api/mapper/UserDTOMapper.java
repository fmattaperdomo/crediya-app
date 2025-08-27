package net.fmattaperdomo.api.mapper;

import net.fmattaperdomo.api.dto.request.UserRequest;
import net.fmattaperdomo.api.dto.response.UserResponse;
import net.fmattaperdomo.model.user.User;
import org.mapstruct.Mapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    UserResponse toResponse(User user);
    User toModel(UserResponse userResponse);
    User toModelUser(UserRequest userResponse);
}
