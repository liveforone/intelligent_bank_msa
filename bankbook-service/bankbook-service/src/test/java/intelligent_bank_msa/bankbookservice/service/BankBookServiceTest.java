package intelligent_bank_msa.bankbookservice.service;

import intelligent_bank_msa.bankbookservice.dto.bankbook.BankBookRequest;
import intelligent_bank_msa.bankbookservice.dto.bankbook.SuspendRequest;
import intelligent_bank_msa.bankbookservice.model.BankBookState;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankBookServiceTest {

    @Autowired
    BankBookService bankBookService;

    @Autowired
    EntityManager em;

    String createBankBook(String email, String password) {
        BankBookRequest bankBookRequest = BankBookRequest.builder()
                .email(email)
                .password(password)
                .build();

        return bankBookService.saveBankBook(bankBookRequest);
    }

    @Test
    @Transactional
    void saveBankBookTest() {
        //given
        String email = "abc1234@gmail.com";
        String password = "1111";

        //when
        String bankBookNum = createBankBook(email, password);
        em.flush();
        em.clear();

        //then
        Assertions
                .assertThat(bankBookService.getBankBookByBankBookNum(bankBookNum).getBankBookState())
                .isEqualTo(BankBookState.USE);
    }

    @Test
    @Transactional
    void suspendByEmailTest() {
        //given
        String email = "abc1234@gmail.com";
        String password = "1111";
        String bankBookNum = createBankBook(email, password);

        //when
        SuspendRequest suspendRequest = new SuspendRequest();
        suspendRequest.setEmail(email);
        suspendRequest.setPassword(password);
        bankBookService.suspendByEmail(suspendRequest);
        em.flush();
        em.clear();

        //then
        Assertions
                .assertThat(bankBookService.getBankBookByBankBookNum(bankBookNum).getBankBookState())
                .isEqualTo(BankBookState.SUSPEND);
    }
}