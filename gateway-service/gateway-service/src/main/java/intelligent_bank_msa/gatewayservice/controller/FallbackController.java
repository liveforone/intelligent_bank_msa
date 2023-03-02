package intelligent_bank_msa.gatewayservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/user")
    public Mono<String> fallbackUser() {
        String errorMessage = "유저 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @GetMapping("/bankbook")
    public Mono<String> fallbackBankbook() {
        String errorMessage = "통장 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @GetMapping("/record")
    public Mono<String> fallbackRecord() {
        String errorMessage = "거래내역 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @GetMapping("/atm")
    public Mono<String> fallbackAtm() {
        String errorMessage = "ATM 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @GetMapping("/remit")
    public Mono<String> fallbackRemit() {
        String errorMessage = "송금 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }
}
