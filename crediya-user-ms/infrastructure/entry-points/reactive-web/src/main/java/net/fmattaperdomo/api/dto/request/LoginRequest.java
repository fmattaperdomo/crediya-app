package net.fmattaperdomo.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "User: email is required")
    @Email(message = "User: email must be a valid email address")
    private String email;
    @NotNull(message = "User: password is required")
    private String password;
}
