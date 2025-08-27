package net.fmattaperdomo.api.dto.request;

import jakarta.validation.constraints.*;
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
public class UserRequest {
    private Long userId;
    @NotNull(message = "User: firstname is required")
    @Size(min = 3,
            max = 100,
            message = "User: firstname name must be between 3 and 100 characters")
    private String firstname;
    @NotNull(message = "User: lastname is required")
    @Size(min = 3,
            max = 100,
            message = "User: last name must be between 3 and 100 characters")
    private String lastname;

    private LocalDate birthdate;
    @NotNull(message = "User: email is required")
    @Email(message = "User: email must be a valid email address")
    private String email;
    @NotNull(message = "User: password  is required")
    private String password;
    @NotNull(message = "User: Number ID  is required")
    @Size(min = 3,
            max = 20,
            message = "User: Number ID name must be between 3 and 20 characters")
    private String nid;
    @NotNull(message = "User: address  is required")
    private String address;
    @NotNull(message = "User: phone is required")
    private String phone;
    @NotNull(message = "User: base salary is required")
    @Min(value = 1, message = "User: base salary must be at least 1")
    @Max(value = 15000000, message = "User: base salary size cannot exceed 15000000")
    private BigDecimal baseSalary;
    @NotNull(message = "User: base salary is required")
    private Integer roleId;
}
