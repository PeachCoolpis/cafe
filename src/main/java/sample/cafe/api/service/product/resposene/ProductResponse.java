package sample.cafe.api.service.product.resposene;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafe.domain.product.Product;
import sample.cafe.domain.product.ProductSellingStatus;
import sample.cafe.domain.product.ProductType;

@Getter
@NoArgsConstructor
public class ProductResponse {
    
    private Long id;
    
    private String productNumber;
    
    private ProductType type;
    
    private ProductSellingStatus sellingStatus;
    
    private String name;
    
    private int price;
    
    public static ProductResponse of(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.id = product.getId();
        productResponse.productNumber = product.getProductNumber();
        productResponse.type = product.getType();
        productResponse.sellingStatus = product.getSellingStatus();
        productResponse.name = product.getName();
        productResponse.price = product.getPrice();
        return productResponse;
    }
    @Builder
    public ProductResponse(Long id, String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }
}
