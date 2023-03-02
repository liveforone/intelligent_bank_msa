package intelligent_bank_msa.remitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class RemitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemitServiceApplication.class, args);
	}

}
