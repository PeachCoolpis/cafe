package sample.cafe.api.controller.product.dto.request;

import lombok.Getter;
import sample.cafe.domain.product.Product;
import sample.cafe.domain.product.ProductSellingStatus;
import sample.cafe.domain.product.ProductType;

@Getter
public class ProductCreateRequest {
    
    
    private ProductType type;
    
    private ProductSellingStatus sellingStatus;
    
    private String name;
    
    private int price;
    
    
    private ProductCreateRequest(ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }
    
    public Product toEntity(String nextProductNumber) {
        return Product.builder()
                .productNumber(nextProductNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}
