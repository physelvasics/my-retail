package com.myretail.product.domain;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * This represents the cassandra table product_detail.
 *
 * @author Selvaraj Karuppusamy
 */
@Table("product_detail")
@ProductDetailConstraint
public class ProductDetail {

    /**
     * This is the primary column of the table which represents product id of Integer type and this cannot be NULL.
     *
     * ex: 13860428
     */
    @PrimaryKey
    @CassandraType(type = DataType.Name.INT)
    private Integer id;

    /**
     * This String type field represents the name/title of the product and this is nullable.
     *
     * ex: "SpongeBob SquarePants: SpongeBob's Frozen Face-off"
     */
    private String name;

    /**
     * This String type field contains current price and currency code of the item.
     *
     * ex: {"value":15.49,"currency_code":"USD"}
     */
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

    //TODO: Need to find better solution to avoid creating ObjectMapper instance on every get and set.
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
