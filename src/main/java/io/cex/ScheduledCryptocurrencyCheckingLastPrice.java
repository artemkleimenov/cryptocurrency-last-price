package io.cex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.cex.repository.CryptocurrencyPriceRepository;

@Component
public class ScheduledCryptocurrencyCheckingLastPrice {
	
	@Autowired
	private CryptocurrencyPriceRepository repository;
	
	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
	   final RestTemplate restTemplate = new RestTemplate();

	   List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
	   MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	   converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
	   messageConverters.add(converter);
	   restTemplate.setMessageConverters(messageConverters);

	   return restTemplate;
	}
	
	@Scheduled(cron="*/10 * * * * *")
	public void lastprice() throws Exception {
		
		Cryptocurrency cryptocurrencyLastPriceBTC = restTemplate.getForObject(
				"https://cex.io/api/last_price/BTC/USD", Cryptocurrency.class);
		Cryptocurrency cryptocurrencyLastPriceETH = restTemplate.getForObject(
				"https://cex.io/api/last_price/ETH/USD", Cryptocurrency.class);
		Cryptocurrency cryptocurrencyLastPriceXRP = restTemplate.getForObject(
				"https://cex.io/api/last_price/XRP/USD", Cryptocurrency.class);
		
		repository.save(cryptocurrencyLastPriceBTC);
		repository.save(cryptocurrencyLastPriceETH);
		repository.save(cryptocurrencyLastPriceXRP);
	}
}
