package linax.org.radixtechassessment.repository;

import linax.org.radixtechassessment.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
