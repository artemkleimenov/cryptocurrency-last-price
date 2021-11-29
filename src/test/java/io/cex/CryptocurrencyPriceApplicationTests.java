package io.cex;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CryptocurrencyPriceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void minPriceDefault() throws Exception {
		this.mockMvc.perform(get("/cryptocurrencies/minprice"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.curr2").value("USD"));
	}
	
	@Test
	void minPriceWithParam() throws Exception {
		this.mockMvc.perform(get("/cryptocurrencies/minprice").param("name", "BTC"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.curr1").value("BTC"));
	}
	
	@Test
	void maxPriceDefault() throws Exception {
		this.mockMvc.perform(get("/cryptocurrencies/maxprice"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.curr2").value("USD"));
	}
	
	@Test
	void maxPriceWithParam() throws Exception {
		this.mockMvc.perform(get("/cryptocurrencies/maxprice").param("name", "BTC"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.curr1").value("BTC"));
	}
	
	@Test
	void priceWithPageAndSize() throws Exception {
		this.mockMvc.perform(get("/cryptocurrencies").param("name", "BTC").param("page", "0").param("size", "10"))
		.andDo(print()).andExpect(status().isOk());
	}
}
