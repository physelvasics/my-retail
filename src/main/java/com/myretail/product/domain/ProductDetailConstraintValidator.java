package com.myretail.product.domain;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductDetailConstraintValidator implements ConstraintValidator<ProductDetailConstraint, ProductDetail> {
    @Override
    public void initialize(ProductDetailConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(ProductDetail productDetail, ConstraintValidatorContext constraintValidatorContext) {

        if (productDetail == null ||
                StringUtils.isEmpty(productDetail.getName()) ||
                productDetail.getId() == null ||
                productDetail.getPrice() == null ||
                StringUtils.isEmpty(productDetail.getPrice().getCurrencyCode()) ||
                productDetail.getPrice().getValue() == null
        ) {
            return false;
        }

        return true;
    }
}
