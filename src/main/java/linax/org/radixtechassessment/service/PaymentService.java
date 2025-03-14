package linax.org.radixtechassessment.service;

import linax.org.radixtechassessment.dto.PaymentDto;
import linax.org.radixtechassessment.mapper.PaymentMapper;
import linax.org.radixtechassessment.model.Loan;
import linax.org.radixtechassessment.model.Payment;
import linax.org.radixtechassessment.repository.LoanRepository;
import linax.org.radixtechassessment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final LoanRepository loanRepository;
    private final PaymentMapper paymentMapper;

    public PaymentDto save(PaymentDto paymentDto) {

        Loan loan = loanRepository.findById(paymentDto.getLoan().getId())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Payment payment = Optional.of(paymentRepository.save(paymentMapper.toEntity(paymentDto)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        loan.setBalanceAmount(loan.getBalanceAmount() - payment.getAmount());
        payment.setLoan(loanRepository.save(loan));

        return paymentMapper.toDto(payment);
    }

    public List<PaymentDto> findAll() {
        return paymentMapper.toDtoList(paymentRepository.findAll());
    }
}
