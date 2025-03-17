package linax.org.radixtechassessment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Long id;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be at least 0")
    private Double amount;

    private LocalDateTime createdAt;

    private LoanDto loan;
}
