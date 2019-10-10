package com.myretail.product.service;

import com.myretail.product.config.ProductRestClient;
import com.myretail.product.dao.ProductDetailRepo;
import com.myretail.product.domain.Product;
import com.myretail.product.domain.ProductDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * This service acts as layer between controller and DAO and poses business logic to store and retrieve data
 * to and from various resources like DB and product service.
 *
 *
 */
@Service
public class ProductDetailService {

    private static final Logger log = LoggerFactory.getLogger(ProductDetailService.class);

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Autowired
    private ProductRestClient productRestClient;

    public void updateProductDetail(ProductDetail productDetail){
        productDetailRepo.save(productDetail);
        log.info("Updated product detail, id={}", productDetail.getId());
    }

    public ProductDetail getProductDetail(Integer id){
        log.info("Retrieving product detail, id={}", id);
        ProductDetail productDetail = productDetailRepo.findById(id).orElse(null);
        //Assume that the DB data is the source of truth
        if(productDetail != null){
            if(StringUtils.isEmpty(productDetail.getName())){
                productDetail.setName(getProductDetailName(id));
            }
        } else {
            log.info("Product detail not found in the DB, id={}", id);
        }

        return productDetail;
    }

    private String getProductDetailName(Integer id){

        if(id == null){
            return null;
        }
        Product product = productRestClient.getProductById(id);

        String productName = null;

        try{
            productName = product.getItem().getProductDescription().getTitle();
        } catch (NullPointerException ex){
            log.info("Product endpoint does not have name, id={}", id);
        } catch (Exception ex){
            throw ex;
        }

        return productName;
    }
}
