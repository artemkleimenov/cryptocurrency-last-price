package io.cex.service;


import java.io.IOException;
import java.io.Writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.cex.controller.CryptocurrencyMaxPriceController;
import io.cex.controller.CryptocurrencyMinPriceController;

@Service
public class CSVExportService { 
	
	private static final Logger log = LoggerFactory.getLogger(CSVExportService.class);
	
	@Autowired
	CryptocurrencyMinPriceController cryptocurrencyMinPriceController;
	
	@Autowired
	CryptocurrencyMaxPriceController cryptocurrencyMaxPriceController;
	
	public void writeToCSV(Writer writer) {
		CSVFormat csvFormat = CSVFormat.Builder.create()
				.setHeader("Cryptocurrency Name", "Min Price", "Max Price")
				.build();
        
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
                csvPrinter.printRecord(
                		cryptocurrencyMinPriceController.minPrice("BTC")
                		.getBody()
                		.getCurr1(), 
                		cryptocurrencyMinPriceController.minPrice("BTC")
                		.getBody()
                		.getLprice(),
                		cryptocurrencyMaxPriceController.maxPrice("BTC")
                		.getBody()
                		.getLprice());
                
                csvPrinter.printRecord(
                		cryptocurrencyMinPriceController.minPrice("ETH")
                		.getBody()
                		.getCurr1(), 
                		cryptocurrencyMinPriceController.minPrice("ETH")
                		.getBody()
                		.getLprice(), 
                		cryptocurrencyMaxPriceController.maxPrice("ETH")
                		.getBody()
                		.getLprice());
                
                csvPrinter.printRecord(
                		cryptocurrencyMinPriceController.minPrice("XRP")
                		.getBody()
                		.getCurr1(), 
                		cryptocurrencyMinPriceController.minPrice("XRP")
                		.getBody()
                		.getLprice(), 
                		cryptocurrencyMaxPriceController.maxPrice("XRP")
                		.getBody()
                		.getLprice());
        } catch (IOException e) {
            log.error("Error while writing CSV ", e);
        }
    }
	
}
