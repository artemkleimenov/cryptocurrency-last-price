package io.cex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cex.Cryptocurrency;
import io.cex.service.CryptocurrencyPageService;

@RestController
public class CryptocurrenciesPricePageController {
	
	@Autowired
	private CryptocurrencyPageService service;
	
	@GetMapping("/cryptocurrencies")
	public ResponseEntity<List<Cryptocurrency>> getAll(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
		
		List<Cryptocurrency> list = service.getAll(name, pageNumber, pageSize).getContent();
		 
        return new ResponseEntity<List<Cryptocurrency>>(list, new HttpHeaders(), HttpStatus.OK); 
	}
}
