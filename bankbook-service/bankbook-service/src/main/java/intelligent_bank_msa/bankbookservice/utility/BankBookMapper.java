package intelligent_bank_msa.bankbookservice.utility;

import intelligent_bank_msa.bankbookservice.dto.bankbook.BankBookRequest;
import intelligent_bank_msa.bankbookservice.dto.bankbook.BankBookResponse;
import intelligent_bank_msa.bankbookservice.domain.BankBook;

public class BankBookMapper {

    public static BankBook dtoToEntity(BankBookRequest bankBookRequest) {
        return BankBook.builder()
                .id(bankBookRequest.getId())
                .bankBookNum(bankBookRequest.getBankBookNum())
                .email(bankBookRequest.getEmail())
                .password(bankBookRequest.getPassword())
                .balance(bankBookRequest.getBalance())
                .bankBookState(bankBookRequest.getBankBookState())
                .build();
    }

    public static BankBookResponse entityToDtoDetail(BankBook bankBook) {
        return BankBookResponse.builder()
                .id(bankBook.getId())
                .bankBookNum(bankBook.getBankBookNum())
                .email(bankBook.getEmail())
                .balance(bankBook.getBalance())
                .bankBookState(bankBook.getBankBookState())
                .createdDate(bankBook.getCreatedDate())
                .build();
    }
}
