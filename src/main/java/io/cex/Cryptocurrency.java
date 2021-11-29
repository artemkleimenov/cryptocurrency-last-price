package io.cex;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "CryptocurrencyLastPrice")
public class Cryptocurrency {
	
	@Id
	private String Id;
	
	@Field("Price")
	private double lprice;
	
	@Field("CryptocurrencyName")
	private String curr1;
	
	@Field("USD")
	private String curr2;
	
	public Cryptocurrency() {
	}
	
	public Cryptocurrency(double lprice, String curr1, String curr2) {
		this.lprice = lprice;
		this.curr1 = curr1;
		this.curr2 = curr2;
	}

	public double getLprice() {
		return lprice;
	}

	public void setLprice(double lprice) {
		this.lprice = lprice;
	}

	public String getCurr1() {
		return curr1;
	}

	public String getCurr2() {
		return curr2;
	}

	public void setCurr1(String curr1) {
		this.curr1 = curr1;
	}

	public void setCurr2(String curr2) {
		this.curr2 = curr2;
	}
}
