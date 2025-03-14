package linax.org.radixtechassessment.controller;

import linax.org.radixtechassessment.dto.LoanDto;
import linax.org.radixtechassessment.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @PutMapping("")
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

}
