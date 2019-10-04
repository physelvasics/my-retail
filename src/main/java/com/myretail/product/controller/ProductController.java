package com.myretail.product.controller;

import com.myretail.product.domain.ProductDetail;
import com.myretail.product.service.ProductDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping(path = "/products/{id}", produces = "application/json")
    ProductDetail getProductDetails(@PathVariable Integer id) {
        log.info("Incoming get request, id={}", id);
        return productDetailService.getProductDetail(id);
    }

    @PutMapping(path = "/products/{id}", consumes = "application/json")
    String getProductDetails(@PathVariable Integer id,
                             @RequestBody ProductDetail productDetail,
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
