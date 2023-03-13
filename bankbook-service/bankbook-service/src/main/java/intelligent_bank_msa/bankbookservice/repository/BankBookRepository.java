package intelligent_bank_msa.bankbookservice.repository;

import intelligent_bank_msa.bankbookservice.domain.BankBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankBookRepository extends JpaRepository<BankBook, Long>, BankBookRepositoryCustom {
}
