package intelligent_bank_msa.bankbookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BankbookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankbookServiceApplication.class, args);
	}

}
