package sample.cafe.api.service.product.resposene;

import lombok.Getter;
import sample.cafe.product.Product;
import sample.cafe.product.ProductSellingStatus;
import sample.cafe.product.ProductType;

@Getter
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
}
