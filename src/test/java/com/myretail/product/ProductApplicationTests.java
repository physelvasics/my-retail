package com.myretail.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-local.properties")
public class ProductApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getProductDetail() throws Exception{
		mvc.perform( MockMvcRequestBuilders
				.get("/v1/products/13860428")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("13860428"));
	}

	@Test
	public void getProductDetailInvalidRequest() throws Exception{
		mvc.perform( MockMvcRequestBuilders
				.get("/v1/products/abcd")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getProductDetailNoData() throws Exception{
		mvc.perform( MockMvcRequestBuilders
				.get("/v1/products/12345")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	public void putProductDetail() throws Exception{
		mvc.perform( MockMvcRequestBuilders
				.put("/v1/products/13860428")
				.content("{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":14.49,\"currency_code\":\"USD\"}}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("success"));
	}

	@Test
	public void putProductDetailInvalidRequest() throws Exception{
		mvc.perform( MockMvcRequestBuilders
				.put("/v1/products/13860428")
				.content("{\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":14.49,\"currency_code\":\"USD\"}}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
}
