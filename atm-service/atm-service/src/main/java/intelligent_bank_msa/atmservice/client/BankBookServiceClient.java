package intelligent_bank_msa.atmservice.client;

import intelligent_bank_msa.atmservice.dto.bankbook.BankBookResponse;
import intelligent_bank_msa.atmservice.dto.bankbook.PasswordCheckRequest;
import intelligent_bank_msa.atmservice.dto.bankbook.PasswordCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "bankbook-service")
public interface BankBookServiceClient {

    @PostMapping("/bank-info/{bankBookNum}")
    BankBookResponse getBankBook(@PathVariable("bankBookNum") String bankBookNum);

    @PostMapping("/password/check")
    PasswordCheckResponse checkBankPassword(@RequestBody PasswordCheckRequest passwordCheckRequest);
}
