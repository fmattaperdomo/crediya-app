package net.fmattaperdomo.model.user;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
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
