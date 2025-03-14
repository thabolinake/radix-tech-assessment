package linax.org.radixtechassessment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoanDto {
    private Long id;
    private Double amount;
    private Integer term;
    private Double balanceAmount;
    private LocalDateTime createdAt;
}
