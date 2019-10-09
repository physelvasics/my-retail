package com.myretail.product;

import co.elastic.apm.opentracing.ElasticApmTracer;
import io.opentracing.Tracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource(value = "classpath:application-${environment:local}.properties", ignoreResourceNotFound = true),
        @PropertySource("classpath:default.properties")})
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    public Tracer tracer() {
        return new ElasticApmTracer();
    }
}
