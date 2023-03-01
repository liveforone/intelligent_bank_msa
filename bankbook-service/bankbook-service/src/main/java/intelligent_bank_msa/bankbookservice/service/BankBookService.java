package intelligent_bank_msa.bankbookservice.service;

import intelligent_bank_msa.bankbookservice.dto.BankBookRequest;
import intelligent_bank_msa.bankbookservice.dto.SuspendRequest;
import intelligent_bank_msa.bankbookservice.model.BankBook;
import intelligent_bank_msa.bankbookservice.model.BankBookState;
import intelligent_bank_msa.bankbookservice.repository.BankBookRepository;
import intelligent_bank_msa.bankbookservice.utility.BankBookMapper;
import intelligent_bank_msa.bankbookservice.utility.BankBookPassword;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BankBookService {

    private final BankBookRepository bankBookRepository;

    public BankBook getBankBookByEmail(String email) {
        return bankBookRepository.findOneByEmail(email);
    }

    @Transactional
    public String saveBankBook(BankBookRequest bankBookRequest) {
        String encodedPassword = BankBookPassword.encodePassword(bankBookRequest.getPassword());
        String bankBookNum = RandomStringUtils.randomNumeric(13);

        bankBookRequest.setBankBookNum(bankBookNum);
        bankBookRequest.setPassword(encodedPassword);
        bankBookRequest.setBankBookState(BankBookState.USE);

        return bankBookRepository.save(
                BankBookMapper.dtoToEntity(bankBookRequest)
        ).getBankBookNum();
    }

    @Transactional
    public void suspendByEmail(SuspendRequest suspendRequest) {
        bankBookRepository.suspendByEmail(suspendRequest.getEmail());
    }

    @Transactional
    public void cancelSuspendByEmail(SuspendRequest suspendRequest) {
        bankBookRepository.cancelSuspendByEmail(suspendRequest.getEmail());
    }
}