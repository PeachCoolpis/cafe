package sample.cafe.api.controller.product;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cafe.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafe.api.service.product.ProductService;
import sample.cafe.api.service.product.resposene.ProductResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    @PostMapping("/api/v1/products/new")
    private void createProduct(@RequestBody ProductCreateRequest request) {
        productService.create(request);
    }
    
    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts() {
        return productService.getSellingProducts();
    }
}
