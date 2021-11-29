package io.cex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CryptocurrencyPriceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyPriceApplication.class, args);
	}
	
}
