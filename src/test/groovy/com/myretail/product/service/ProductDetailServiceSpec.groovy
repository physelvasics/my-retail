package com.myretail.product.service

import com.myretail.product.config.ProductClient
import com.myretail.product.dao.ProductDetailRepo
import com.myretail.product.domain.Item
import com.myretail.product.domain.Product
import com.myretail.product.domain.ProductDescription
import com.myretail.product.domain.ProductDetail
import spock.lang.Specification

class ProductDetailServiceSpec extends Specification{

    ProductDetailRepo productDetailRepo = Mock(ProductDetailRepo)
    ProductClient productClient = Mock(ProductClient)

    ProductDetailService productDetailService = new ProductDetailService(
            productDetailRepo: productDetailRepo,
            productClient: productClient
    )

    def "Product detail name not available in database"(){

        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, current_price: "{\"value\":14.49,\"currency_code\":\"USD\"}")

        when:
        ProductDetail result = productDetailService.getProductDetail(1234)

        then:
        1 * productDetailRepo.findById(1234) >> new Optional<ProductDetail>(new ProductDetail(id: 1234, current_price: "{\"value\":14.49,\"currency_code\":\"USD\"}"))
        1 * productClient.getProductById(1234) >> new Product(item: new Item(productDescription: new ProductDescription(title: "Sample item Desc")))
        0 * _

        result.id==1234
        result.name == "Sample item Desc"
    }

    def "Product detail name available in database"(){

        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, current_price: "{\"value\":14.49,\"currency_code\":\"USD\"}")

        when:
        ProductDetail result = productDetailService.getProductDetail(1234)

        then:
        1 * productDetailRepo.findById(1234) >> new Optional<ProductDetail>(new ProductDetail(id: 1234, name: "The Big Lebowski (Blu-ray)", current_price: "{\"value\":14.49,\"currency_code\":\"USD\"}"))
        0 * _

        result.id==1234
        result.name == "The Big Lebowski (Blu-ray)"
    }

    def "Product detail not available in database"(){

        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, current_price: "{\"value\":14.49,\"currency_code\":\"USD\"}")

        when:
        ProductDetail result = productDetailService.getProductDetail(1234)

        then:
        1 * productDetailRepo.findById(1234) >> new Optional<ProductDetail>()
        0 * _

        result == null
    }

}
