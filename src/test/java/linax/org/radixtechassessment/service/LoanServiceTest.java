package linax.org.radixtechassessment.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import linax.org.radixtechassessment.dto.LoanDto;
import linax.org.radixtechassessment.mapper.LoanMapper;
import linax.org.radixtechassessment.model.Loan;
import linax.org.radixtechassessment.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanMapper loanMapper;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    void testSaveLoan_ValidInput() {
        // Arrange
        LoanDto loanDto = new LoanDto();
        loanDto.setAmount(1500.0);
        loanDto.setTerm(12);

        Loan loan = new Loan();
        loan.setAmount(1500.0);
        loan.setTerm(12);
        loan.setBalanceAmount(1500.0);

        when(loanMapper.toEntity(loanDto)).thenReturn(loan);
        when(loanRepository.save(loan)).thenReturn(loan);
        when(loanMapper.toDto(loan)).thenReturn(loanDto);

        // Act
        LoanDto newLoan = loanService.save(loanDto);

        // Assert
        assertNotNull(newLoan);
        assertEquals(1500.0, newLoan.getAmount());
        assertEquals(12, newLoan.getTerm());

        verify(loanMapper, times(1)).toEntity(loanDto);
        verify(loanRepository, times(1)).save(loan);
        verify(loanMapper, times(1)).toDto(loan);
    }

    @Test
    void testSaveLoan_InvalidAmount() {
        // Arrange
        LoanDto loanDto = new LoanDto();
        loanDto.setAmount(-100.0); // Invalid amount
        loanDto.setTerm(12);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> loanService.save(loanDto));
        assertEquals(BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testSaveLoan_InvalidTerm() {
        // Arrange
        LoanDto loanDto = new LoanDto();
        loanDto.setAmount(1500.0);
        loanDto.setTerm(-5); // Invalid term

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> loanService.save(loanDto));
        assertEquals(BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testSaveLoan_MappingFailure() {
        // Arrange
        LoanDto loanDto = new LoanDto();
        loanDto.setAmount(1500.0);
        loanDto.setTerm(12);

        when(loanMapper.toEntity(loanDto)).thenReturn(null); // Simulate mapping failure

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> loanService.save(loanDto));
        assertEquals(INTERNAL_SERVER_ERROR, exception.getStatusCode());
    }
}
