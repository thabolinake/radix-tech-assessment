package linax.org.radixtechassessment.service;

import linax.org.radixtechassessment.dto.LoanDto;
import linax.org.radixtechassessment.mapper.LoanMapper;
import linax.org.radixtechassessment.model.Loan;
import linax.org.radixtechassessment.repository.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanMapper loanMapper;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    void testSaveLoan_Valid(){
        //Arrange
        Loan loan = new Loan();

        LoanDto loanDto = new LoanDto();
        loanDto.setAmount(1500.0);
        loanDto.setTerm(12);

        Mockito.when(loanMapper.toEntity(loanDto)).thenReturn(loan);
        Mockito.when(loanRepository.save(loan)).thenReturn(loan);
        Mockito.when(loanMapper.toDto(loan)).thenReturn(loanDto);

        //Act
        LoanDto newLoan = loanService.save(loanDto);
        Assertions.assertNotNull(newLoan);

        Mockito.verify(loanMapper, Mockito.times(1)).toEntity(loanDto);
        Mockito.verify(loanRepository, Mockito.times(1)).save(loan);
        Mockito.verify(loanMapper, Mockito.times(1)).toDto(loan);
    }
}
