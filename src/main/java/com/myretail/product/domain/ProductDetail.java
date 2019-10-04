package com.myretail.product.domain;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("product_detail")
public class ProductDetail {

    @PrimaryKey
    @CassandraType(type = DataType.Name.INT)
    private Integer id;
    private String name;
    private String current_price;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //todo Need to find better solution to avoid creating ObjectMapper object on get and set
    @JsonGetter("current_price")
    public Price getPrice() {
        try{
            return (new ObjectMapper()).readValue(current_price, Price.class);
        } catch (Exception e){
            //eat the exception and return null
        }
        return null;
    }
    @JsonSetter("current_price")
    public void setPrice(Price price) {
        try{
            this.current_price = (new ObjectMapper()).writeValueAsString(price);
        } catch (Exception ex){
            //eat exception and set null
        }
    }
}
