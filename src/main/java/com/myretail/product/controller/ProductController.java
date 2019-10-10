package com.myretail.product.controller;

import com.myretail.product.domain.ProductDetail;
import com.myretail.product.service.ProductDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * This rest controller has two end points to store and retrieve product details using ProductDetailService.
 *
 * @author Selvaraj Karuppusamy
 */
@RestController
@RequestMapping(value = "/v1")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDetailService productDetailService;

    /**
     * This GET endpoint will serve product detail for given id.
     *
     * <b>Note: </b>The response may have one of following.
     * <p>
     *    <ul>
     *       <li>ProductDetail data with status code 200 in case of successful retrieval.</li>
     *       <li>null value with status code of 500 in case of any exception.</li>
     *       <li>null value with status code of 400 in case of invalid input.</li>
     *    </ul>
     * </p>
     *
     * @param id       is a product id for which the product details has to be retrieved.
     * @param response is used to send http status codes based on data retrieval operation status.
     * @return ProductDetail for given id.
     */
    @GetMapping(path = "/products/{id}", produces = "application/json")
    ProductDetail getProductDetails(@PathVariable Integer id, HttpServletResponse response) {
        log.info("Incoming get request, id={}", id);

        ProductDetail productDetail = null;
        try {
            productDetail = productDetailService.getProductDetail(id);
            if (productDetail == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return productDetail;
    }

    /**
     * This PUT endpoint will receive and update the product detail for given id.
     *
     * <b>Note: </b>The response may have one of following.
     * <p>
     *    <ul>
     *      <li>'success' message with status code 200 in case of successful update.</li>
     *      <li>'failure' message with status code of 500 in case of any exception.</li>
     *      <li>400 in case of invalid input.</li>
     *    </ul>
     * </p>
     *
     * @param id            is a product id for which the product details has to be updated.
     * @param productDetail which has to be updated.
     * @param response      is used to send the http status based on request processing status.
     * @return String status.
     */
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
