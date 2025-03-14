package linax.org.radixtechassessment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Long id;
    private Double amount;
    private LocalDateTime createdAt;
    private LoanDto loan;
}
