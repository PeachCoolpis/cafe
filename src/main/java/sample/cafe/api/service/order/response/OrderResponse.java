package sample.cafe.api.service.order.response;

import lombok.Getter;
import sample.cafe.api.service.product.resposene.ProductResponse;
import sample.cafe.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {
    
    private Long id;
    
    private OrderStatus orderStatus;
    
    private int totalPrice;
    
    private LocalDateTime registeredDateTime;
    
    private List<ProductResponse> products;
    
}
