package net.fmattaperdomo.r2dbc.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("user_crediya")
@Builder
public class UserEntity {
    @Id
    @Column(value = "id_user")
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
    @Column(value = "id_role")
    private Integer roleId;
}

