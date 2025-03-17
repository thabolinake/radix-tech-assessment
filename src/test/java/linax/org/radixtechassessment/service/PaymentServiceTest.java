package linax.org.radixtechassessment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void testGetReducedLoanBalanceAmount_ReturnZero(){
        double newLoanBalanceAmount = paymentService.getReducedLoanBalanceAmount(5500.00, 5800.00);
        Assertions.assertEquals(0, newLoanBalanceAmount);
    }

}
