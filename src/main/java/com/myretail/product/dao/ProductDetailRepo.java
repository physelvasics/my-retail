package com.myretail.product.dao;

import com.myretail.product.domain.ProductDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository will have methods to perform database operations.
 *
 * @author Selvaraj Karuppusamy
 */
@Repository
public interface ProductDetailRepo extends CrudRepository<ProductDetail, Integer> {
}
