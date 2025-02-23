package sample.cafe.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafe.api.controller.order.request.OrderCreateRequest;
import sample.cafe.api.service.order.response.OrderResponse;
import sample.cafe.domain.order.OrderRepository;
import sample.cafe.domain.product.Product;
import sample.cafe.domain.product.ProductRepository;

import java.awt.image.RasterFormatException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    
    public OrderResponse createOrder(OrderCreateRequest request) {
        List<String> productNumbers = request.getProductNumbers();
        
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        
        
        return null;
    }
}
