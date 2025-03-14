package linax.org.radixtechassessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import linax.org.radixtechassessment.dto.LoanDto;
import linax.org.radixtechassessment.dto.PaymentDto;
import linax.org.radixtechassessment.model.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSavePayment() throws Exception {
        PaymentDto paymentDto = new PaymentDto();

        LoanDto loanDto = new LoanDto();
        Loan loan = new Loan();
        loan.setId(1L);
        loanDto.setId(1L);

        paymentDto.setAmount(100.0);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentDto))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
