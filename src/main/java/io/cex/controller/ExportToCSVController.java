package io.cex.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cex.service.CSVExportService;

@RestController
public class ExportToCSVController {
	
	@Autowired
	CSVExportService csvExportService;
	
    @RequestMapping(path = "/cryptocurrencies/csv")
    public void getMaxAndMinPriceByCurrencyInCSV(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"cryptocyrrencies.csv\"");
        csvExportService.writeToCSV(servletResponse.getWriter());
    }
}
