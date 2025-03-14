package linax.org.radixtechassessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveLoan() throws Exception {

        Loan loan = new Loan();
        loan.setAmount(100.0);
        loan.setTerm(12);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loan))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
