package sample.cafe.domain.orderproduct;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafe.domain.order.Order;
import sample.cafe.domain.product.BaseEntity;
import sample.cafe.domain.product.Product;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    
    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
    
}