package com.myretail.product.domain;

/**
 * This represents ProductDescription of an Item.
 */
public class ProductDescription {

    /**
     * This is String type title of the product.
     *
     * ex: "SpongeBob SquarePants: SpongeBob's Frozen Face-off"
     */
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
