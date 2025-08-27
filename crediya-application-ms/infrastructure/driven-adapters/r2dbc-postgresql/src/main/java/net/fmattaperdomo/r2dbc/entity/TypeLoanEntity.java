package net.fmattaperdomo.r2dbc.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Entity
@Table("type_loan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TypeLoanEntity {
    @Id
    @Column(value = "id_type_loan")
    Integer typeLoanId;
    String name;
    BigDecimal minimumLoan;
    BigDecimal maximumLoan;
    BigDecimal interestRate;
    Boolean automaticValidation;
}
