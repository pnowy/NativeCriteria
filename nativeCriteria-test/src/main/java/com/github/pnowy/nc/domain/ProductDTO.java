package com.github.pnowy.nc.domain;

/**
 * Product DTO with category and supplier entities.
 *
 * @author Przemek Nowak [przemek dot nowak dot pl at gmail.com]
 */
public class ProductDTO {

    private Long productId;
    private String name;
    private Category category;
    private Supplier supplier;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
