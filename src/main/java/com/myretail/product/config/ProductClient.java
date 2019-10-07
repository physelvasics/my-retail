package com.myretail.product.config;

import com.myretail.product.domain.Product;
import com.myretail.product.domain.ProductResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class ProductClient {

    private static final Logger log = LoggerFactory.getLogger(ProductClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${product.endpoint}")
    private String endpoint;

    public Product getProductById(Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<>(headers);

        String url = String.format(endpoint, id);
        ResponseEntity<ProductResponse> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, ProductResponse.class);
        } catch (HttpClientErrorException ex){
            log.warn("Product service returned client error,id={}, status={}",id, ex.getStatusCode());
        } catch (HttpServerErrorException ex){
            log.warn("Product service returned server error, id={}, status={}",id, ex.getStatusCode());
        } catch (Exception ex){
            log.warn("Product service returned unknown error, id={}",id, ex);
        }

        if(responseEntity != null && responseEntity.hasBody()){
            return responseEntity.getBody().getProduct();
        }

        //todo need to optimize
        return null;
    }
}
