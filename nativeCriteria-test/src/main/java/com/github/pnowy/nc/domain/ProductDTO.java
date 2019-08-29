package com.github.pnowy.nc.domain;

import com.google.common.base.MoreObjects;

/**
 * Product DTO with categoryName and supplierName entities.
 *
 * @author Przemek Nowak
 */
public class ProductDTO {

    private Long productId;
    private String productName;
    private String categoryName;
    private String supplierName;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("productId", productId)
            .add("name", productName)
            .add("categoryName", categoryName)
            .add("supplierName", supplierName)
            .toString();
    }
}
