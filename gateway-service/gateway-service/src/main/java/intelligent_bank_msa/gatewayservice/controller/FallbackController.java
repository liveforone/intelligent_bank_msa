package intelligent_bank_msa.gatewayservice.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/user")
    public Mono<String> fallbackUserGet() {
        String errorMessage = "유저 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @PostMapping("/user")
    public Mono<String> fallbackUserPost() {
        String errorMessage = "유저 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @PatchMapping("/user")
    public Mono<String> fallbackUserPatch() {
        String errorMessage = "유저 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @DeleteMapping("/user")
    public Mono<String> fallbackUserDelete() {
        String errorMessage = "유저 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @GetMapping("/bankbook")
    public Mono<String> fallbackBankbookGet() {
        String errorMessage = "통장 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @PostMapping("/bankbook")
    public Mono<String> fallbackBankbookPost() {
        String errorMessage = "통장 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @PatchMapping("/bankbook")
    public Mono<String> fallbackBankbookPatch() {
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

    @PostMapping("/atm")
    public Mono<String> fallbackAtm() {
        String errorMessage = "ATM 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @PostMapping("/remit")
    public Mono<String> fallbackRemit() {
        String errorMessage = "송금 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @GetMapping("/calculate")
    public Mono<String> fallbackCalculateGet() {
        String errorMessage = "통계 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }

    @PostMapping("/calculate")
    public Mono<String> fallbackCalculatePost() {
        String errorMessage = "통계 서비스의 장애로 접근이 불가능합니다."
                + "\n이용에 불편을 드려 죄송합니다.";
        return Mono.just(errorMessage);
    }
}
