package io.cex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cex.Cryptocurrency;

@RestController
public class CryptocurrencyMaxPriceController {

	@Autowired
    private MongoTemplate mongoTemplate;
	
	@GetMapping("/cryptocurrencies/maxprice")
	public ResponseEntity<Cryptocurrency> maxPrice(@RequestParam(value = "name", defaultValue = "") String name) {
		final Query query = new Query()
                .limit(1)
                .with(Sort.by(Sort.Direction.DESC, "lprice"))
                .addCriteria(Criteria.where("curr1").regex(name));
		
		Cryptocurrency maxPrice = mongoTemplate.findOne(query, Cryptocurrency.class);
		
		return new ResponseEntity<Cryptocurrency>(maxPrice, new HttpHeaders(), HttpStatus.OK);
	}
}
