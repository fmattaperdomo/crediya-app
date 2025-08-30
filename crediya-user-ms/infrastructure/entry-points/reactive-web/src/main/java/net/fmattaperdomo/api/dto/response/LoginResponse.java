package net.fmattaperdomo.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response for Login")
public class LoginResponse {
    private String token;
    private String email;
    private Integer roleId;
}

