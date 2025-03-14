package linax.org.radixtechassessment.service;

import jakarta.persistence.EntityNotFoundException;
import linax.org.radixtechassessment.dto.LoanDto;
import linax.org.radixtechassessment.dto.PaymentDto;
import linax.org.radixtechassessment.mapper.LoanMapper;
import linax.org.radixtechassessment.mapper.PaymentMapper;
import linax.org.radixtechassessment.model.Loan;
import linax.org.radixtechassessment.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final PaymentMapper paymentMapper;

    public LoanDto save(LoanDto loanDto) {

        Loan newLoan = loanMapper.toEntity(loanDto);
        newLoan.setBalanceAmount(loanDto.getAmount());

        Loan loan = Optional.of(loanRepository.save(newLoan))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Loan record could not be created!"));

        return loanMapper.toDto(loanRepository.save(loan));
    }

    public LoanDto getOne(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Loan with ID " + id + " not found!"));

        return loanMapper.toDto(loan);
    }

    public List<PaymentDto> getPayments(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Loan with ID " + id + " not found!"));

        return paymentMapper.toDtoList(loan.getPayments());
    }

    public List<LoanDto> findAll() {
        return loanMapper.toListDto(loanRepository.findAll());
    }
}
