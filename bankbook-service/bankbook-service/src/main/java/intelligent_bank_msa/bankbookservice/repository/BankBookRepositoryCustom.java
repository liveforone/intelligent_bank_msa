package intelligent_bank_msa.bankbookservice.repository;

import intelligent_bank_msa.bankbookservice.model.BankBook;

public interface BankBookRepositoryCustom {
    BankBook findOneByEmail(String email);

    BankBook findOneByBankBookNum(String bankBookNum);

    void suspendByEmail(String email);

    void cancelSuspendByEmail(String email);

    void increaseBalance(String bankBookNum, long inputMoney);

    void decreaseBalance(String bankBookNum, long inputMoney);
}
