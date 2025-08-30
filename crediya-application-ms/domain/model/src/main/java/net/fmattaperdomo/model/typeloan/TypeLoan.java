package net.fmattaperdomo.model.typeloan;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TypeLoan {
    private Long typeLoanId;
    private String name;
    private BigDecimal minimumLoan;
    private BigDecimal maximumLoan;
    private BigDecimal interestRate;
    private Boolean automaticValidation;
}
