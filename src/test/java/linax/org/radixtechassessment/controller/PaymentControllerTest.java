package linax.org.radixtechassessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import linax.org.radixtechassessment.dto.LoanDto;
import linax.org.radixtechassessment.dto.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSavePayment() throws Exception {
        // Step 1: Fetch the current loan balance (assuming loan ID 4 exists)
        String loanResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/loans/1") // API to fetch loan details
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()) // Ensure the loan exists
                .andReturn().getResponse().getContentAsString();

        // Extract the previous balance
        double previousBalance = objectMapper.readTree(loanResponse).get("balanceAmount").asDouble();

        // Step 2: Create a valid payment DTO
        LoanDto loanDto = new LoanDto();
        loanDto.setId(4L); // Loan with ID 4

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(100.0); // Payment amount
        paymentDto.setCreatedAt(LocalDateTime.now());
        paymentDto.setLoan(loanDto);

        // Step 3: Make the payment request and validate response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(paymentDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated()) // Expect HTTP 201 Created
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Payment successfully recorded")) // Success message
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(org.hamcrest.Matchers.greaterThan(previousBalance))); // Check balance increased
    }

}
