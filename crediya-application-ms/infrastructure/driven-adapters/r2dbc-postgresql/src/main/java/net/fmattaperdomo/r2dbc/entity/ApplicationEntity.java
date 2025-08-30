package net.fmattaperdomo.r2dbc.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Entity
@Table("application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApplicationEntity {
    @Id
    @Column(value = "id_application")
    Long applicationId;
    BigDecimal amount;
    Integer deadline;
    String email;
    @Column(value = "id_status")
    Integer statusId;
    @Column(value = "id_type_loan")
    Integer typeLoanId;
}
