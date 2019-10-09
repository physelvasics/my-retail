package com.myretail.product.controller

import com.myretail.product.domain.ProductDetail
import com.myretail.product.service.ProductDetailService
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse

class ProductControllerSpec extends Specification{

    ProductDetailService productDetailService = Mock(ProductDetailService)

    ProductController productController = new ProductController(productDetailService: productDetailService)
    HttpServletResponse httpServletResponse = Mock(HttpServletResponse)

    def "Valid get request"(){
        given:
        ProductDetail expected = new ProductDetail(id: 1234, name: "test name")

        when:
        ProductDetail actual = productController.getProductDetails(1234, httpServletResponse)

        then:
        1 * productDetailService.getProductDetail(1234) >> expected
        0 * _

        expected == actual
    }

    def "Valid get request but no data found"(){
        given:

        when:
        ProductDetail actual = productController.getProductDetails(1234, httpServletResponse)

        then:
        1 * productDetailService.getProductDetail(1234) >> null
        1 * httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND)
        0 * _
    }

    def "Valid get request but exception occurred"(){
        given:

        when:
        ProductDetail actual = productController.getProductDetails(1234, httpServletResponse)

        then:
        1 * productDetailService.getProductDetail(1234) >> { throw new Exception("custom exception") }
        1 * httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
        0 * _
    }


    def "Valid put request"(){
        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, name: "test name")

        when:
        String actual = productController.putProductDetails(1234,productDetail, httpServletResponse)

        then:
        1 * productDetailService.updateProductDetail(productDetail)
        0 * _
        "success" == actual
    }

    def "Put request with exception"(){
        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, name: "test name")
        productDetailService.updateProductDetail(productDetail) >> { throw new Exception("custom exception") }

        when:
        String actual = productController.putProductDetails(1234, productDetail, httpServletResponse)

        then:
        1 * productDetailService.updateProductDetail(productDetail) >> { throw new Exception("custom exception") }
        1 * httpServletResponse.setStatus(500)
        0 * _

        "failure" == actual
    }

}
