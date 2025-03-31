package linax.org.radixtechassessment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import linax.org.radixtechassessment.dto.LoanDto;
import linax.org.radixtechassessment.dto.PaymentDto;
import linax.org.radixtechassessment.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loans")
@Tag(name = "Loan Management API", description = "API for managing loans.")
public class LoanController {

    private final LoanService loanService;

    @PostMapping("")
    public ResponseEntity<LoanDto> save(@RequestBody LoanDto loanDto){
        return new ResponseEntity<>(loanService.save(loanDto), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<LoanDto>> getAll() {
        return new ResponseEntity<>(loanService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> getAll(@PathVariable("id") Long id) {
        return new ResponseEntity<>(loanService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/payments")
    public ResponseEntity<List<PaymentDto>> getLoanPayments(@PathVariable("id") Long id) {
        return new ResponseEntity<>(loanService.getPayments(id), HttpStatus.OK);
    }

}
