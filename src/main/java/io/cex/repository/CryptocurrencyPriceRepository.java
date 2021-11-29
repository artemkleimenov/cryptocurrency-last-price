package io.cex.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.cex.Cryptocurrency;

public interface CryptocurrencyPriceRepository extends MongoRepository<Cryptocurrency, String> {

}
