package linax.org.radixtechassessment.controller;

import linax.org.radixtechassessment.dto.PaymentDto;
import linax.org.radixtechassessment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<PaymentDto> save(@RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>(paymentService.save(paymentDto), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<PaymentDto>> getAll() {
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }
}
