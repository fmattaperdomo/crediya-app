package net.fmattaperdomo.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response for User")
public class UserResponse {
    private Long userId;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String nid;
    private String address;
    private String phone;
    private BigDecimal baseSalary;
    private Integer roleId;
}

