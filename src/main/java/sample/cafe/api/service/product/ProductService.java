package sample.cafe.api.service.product;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafe.api.service.product.resposene.ProductResponse;
import sample.cafe.domain.order.Order;
import sample.cafe.domain.product.Product;
import sample.cafe.domain.product.ProductRepository;
import sample.cafe.domain.product.ProductSellingStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository repository;
    
    public List<ProductResponse> getSellingProducts() {
        
        List<Product> products = repository.findAllBySellingStatusIn(ProductSellingStatus.forDisPlay());
        
        Order order = Order.create(products, LocalDateTime.now());
        
        
        return products.stream()
                .map(ProductResponse::of)
                .toList();
        
    }
}
