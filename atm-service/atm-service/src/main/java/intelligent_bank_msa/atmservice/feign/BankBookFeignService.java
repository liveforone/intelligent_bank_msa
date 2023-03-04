package intelligent_bank_msa.atmservice.feign;

import intelligent_bank_msa.atmservice.dto.atm.AtmRequest;
import intelligent_bank_msa.atmservice.dto.feign.BankInfoAtmDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bankbook-service")
public interface BankBookFeignService {

    @PostMapping("/bank-info/atm")
    BankInfoAtmDto getBankBook(@RequestBody AtmRequest atmRequest);
}
