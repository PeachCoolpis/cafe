package sample.cafe.api.service.product;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafe.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafe.api.service.product.request.ProductCreateServiceRequest;
import sample.cafe.api.service.product.resposene.ProductResponse;
import sample.cafe.domain.order.Order;
import sample.cafe.domain.product.Product;
import sample.cafe.domain.product.ProductRepository;
import sample.cafe.domain.product.ProductSellingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    
    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        String nextProductNumber = createNextProductNumber();
        
        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);
        
        return ProductResponse.of(savedProduct);
    }
    
    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());
        
        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
    
    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }
        
        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;
        
        return String.format("%03d", nextProductNumberInt);
    }
}
