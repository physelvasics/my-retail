package com.myretail.product.controller;

import com.myretail.product.domain.ProductDetail;
import com.myretail.product.service.ProductDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping(path = "/products/{id}", produces = "application/json")
    ProductDetail getProductDetails(@PathVariable Integer id, HttpServletResponse response) {
        log.info("Incoming get request, id={}", id);

        ProductDetail productDetail = null;
        try{
            productDetail = productDetailService.getProductDetail(id);
            if(productDetail == null){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return productDetail;
    }

    @PutMapping(path = "/products/{id}", consumes = "application/json")
    String putProductDetails(@PathVariable Integer id,
                             @Valid @RequestBody ProductDetail productDetail,
                             HttpServletResponse response) {
        log.info("Incoming put request, id={}", id);

        try {
            productDetailService.updateProductDetail(productDetail);
            return "success";
        } catch (Exception e) {
            log.error("Exception while updating product detail", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return "failure";
    }
}
