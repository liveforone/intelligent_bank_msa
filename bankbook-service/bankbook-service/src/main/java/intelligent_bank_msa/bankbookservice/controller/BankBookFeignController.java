package intelligent_bank_msa.bankbookservice.controller;

import intelligent_bank_msa.bankbookservice.dto.feign.*;
import intelligent_bank_msa.bankbookservice.domain.BankBook;
import intelligent_bank_msa.bankbookservice.service.BankBookService;
import intelligent_bank_msa.bankbookservice.utility.BankBookPassword;
import intelligent_bank_msa.bankbookservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BankBookFeignController {

    private final BankBookService bankBookService;

    @PostMapping("/bank-info/atm")
    public ResponseEntity<?> bankInfoAtm(@RequestBody AtmRequest atmRequest) {
        BankBook bankBook = bankBookService.getBankBookByBankBookNum(atmRequest.getBankBookNum());

        BankInfoAtmDto atmDto = BankInfoAtmDto.builder()
                .bankBookNum((CommonUtils.isNull(bankBook)) ? null : bankBook.getBankBookNum())
                .bankBookState((CommonUtils.isNull(bankBook)) ? null : bankBook.getBankBookState())
                .build();

        String inputPassword = atmRequest.getPassword();
        String originalPassword = bankBook.getPassword();
        if (BankBookPassword.isNotMatchPassword(inputPassword, originalPassword)) {
            atmDto.setPasswordStatus(PasswordStatus.FALSE);
        } else {
            atmDto.setPasswordStatus(PasswordStatus.TRUE);
        }

        return ResponseEntity.ok(atmDto);
    }

    @PostMapping("/bank-info/remit")
    public ResponseEntity<?> bankInfoRemit(@RequestBody RemitRequest remitRequest) {
        BankBook senderBankBook = bankBookService.getBankBookByBankBookNum(remitRequest.getSenderBankBookNum());
        BankBook receiverBankBook = bankBookService.getBankBookByBankBookNum(remitRequest.getReceiverBankBookNum());

        BankInfoRemitDto remitDto = BankInfoRemitDto.builder()
                .senderBankBookNum((CommonUtils.isNull(senderBankBook)) ? null : senderBankBook.getBankBookNum())
                .receiverBankBookNum((CommonUtils.isNull(receiverBankBook)) ? null : receiverBankBook.getBankBookNum())
                .senderBankBookState((CommonUtils.isNull(senderBankBook)) ? null : senderBankBook.getBankBookState())
                .receiverBankBookState((CommonUtils.isNull(receiverBankBook)) ? null : receiverBankBook.getBankBookState())
                .build();

        String inputPassword = remitRequest.getPassword();
        String originalPassword = senderBankBook.getPassword();
        if (BankBookPassword.isNotMatchPassword(inputPassword, originalPassword)) {
            remitDto.setPasswordStatus(PasswordStatus.FALSE);
        } else {
            remitDto.setPasswordStatus(PasswordStatus.TRUE);
        }

        return ResponseEntity.ok(remitDto);
    }
}
