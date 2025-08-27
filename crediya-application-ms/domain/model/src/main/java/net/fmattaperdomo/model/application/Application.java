package net.fmattaperdomo.model.application;
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
public class Application {
    Long applicationId;
    BigDecimal amount;
    Integer deadline;
    String nid;
    String email;
    Integer statusId;
    Integer typeLoanId;
}
