package linax.org.radixtechassessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import linax.org.radixtechassessment.dto.LoanDto;
import linax.org.radixtechassessment.dto.PaymentDto;
import linax.org.radixtechassessment.service.LoanService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private PaymentController paymentController;

    @Autowired
    private LoanService loanService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSavePayment_ZeroAmount_ControllerValidation() throws Exception {
        // Arrange
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(0.0);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/payments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(paymentDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testSavePayment_NullLoan_ServiceValidation() {
        // Arrange
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(100.0);
        paymentDto.setLoan(null);

        // Act & Assert
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> paymentController.save(paymentDto));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testSavePayment_Valid_ReducedBalanceAmount() throws Exception {
        // Arrange
        LoanDto loan = new LoanDto();
        loan.setAmount(7500.0);
        loan.setTerm(24);

        LoanDto newLoan = loanService.save(loan);
        double loanBalanceAmount = newLoan.getBalanceAmount();

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(500.0);
        paymentDto.setLoan(newLoan);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/payments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(paymentDto))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(500.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.loan.balanceAmount").value(Matchers.lessThan(loanBalanceAmount)));
    }

}
