package linax.org.radixtechassessment.repository;

import linax.org.radixtechassessment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
