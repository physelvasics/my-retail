package com.myretail.product.domain;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This is the implementation of the constraint validator of ProductDetail.
 *
 * @author Selvaraj Karuppusamy
 */
public class ProductDetailConstraintValidator implements ConstraintValidator<ProductDetailConstraint, ProductDetail> {
    @Override
    public void initialize(ProductDetailConstraint constraintAnnotation) {

    }

    /**
     * This method does the validation operation and returns boolean value based of the validation result.
     * @param productDetail which has to validated.
     * @param constraintValidatorContext current context
     * @return boolean value represents validation status.
     */
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
