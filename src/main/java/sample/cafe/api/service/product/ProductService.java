package sample.cafe.api.service.product;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafe.api.service.product.resposene.ProductResponse;
import sample.cafe.product.Product;
import sample.cafe.product.ProductRepository;
import sample.cafe.product.ProductSellingStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository repository;
    
    public List<ProductResponse> getSellingProducts() {
        
        List<Product> products = repository.findAllBySellingStatusIn(ProductSellingStatus.forDisPlay());
        
        return products.stream()
                .map(ProductResponse::of)
                .toList();
        
    }
}
