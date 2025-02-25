package sample.cafe.api.controller.product;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cafe.api.ApiResponse;
import sample.cafe.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafe.api.service.product.ProductService;
import sample.cafe.api.service.product.resposene.ProductResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    @PostMapping("/api/v1/products/new")
    public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        return ApiResponse.ok(productService.createProduct(request.toServiceRequest()));
    }
    
    @GetMapping("/api/v1/products/selling")
    public ApiResponse<List<ProductResponse>> getSellingProducts() {
        return ApiResponse.ok(productService.getSellingProducts());
    }
}
