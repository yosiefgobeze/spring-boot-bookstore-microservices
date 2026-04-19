package com.samsun.bookstore.catalog.domain;

class ProductMapper {
    // Later we will use Method Reference to do the mapping
    static Product toProduct(ProductEntity productEntity) {
        return new Product(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice());
    }
}
