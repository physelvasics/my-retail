package com.myretail.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.product.config.ProductClient;
import com.myretail.product.dao.ProductDetailRepo;
import com.myretail.product.domain.Product;
import com.myretail.product.domain.ProductDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductDetailService {

    private static final Logger log = LoggerFactory.getLogger(ProductDetailService.class);

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Autowired
    private ProductClient productClient;

    public void updateProductDetail(ProductDetail productDetail){
        productDetailRepo.save(productDetail);
        log.info("Updated product detail, id={}", productDetail.getId());
    }

    public ProductDetail getProductDetail(Integer id){
        log.info("Retrieving product detail, id={}", id);
        ProductDetail productDetail = productDetailRepo.findById(id).orElse(null);
        if(productDetail != null){
            if(StringUtils.isEmpty(productDetail.getName())){
                productDetail.setName(getProductDetailName(id));
            }
            //Assumes the price always present in DB
        }

        return productDetail;
    }

    private String getProductDetailName(Integer id){

        if(id == null){
            return null;
        }
        Product product = productClient.getProductById(id);
        return product.getItem().getProductDescription().getTitle();
    }
}
