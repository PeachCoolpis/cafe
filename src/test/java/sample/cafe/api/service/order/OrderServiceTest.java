package sample.cafe.api.service.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sample.cafe.api.controller.order.request.OrderCreateRequest;
import sample.cafe.api.service.order.response.OrderResponse;
import sample.cafe.domain.product.Product;
import sample.cafe.domain.product.ProductRepository;
import sample.cafe.domain.product.ProductSellingStatus;
import sample.cafe.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafe.domain.product.ProductSellingStatus.*;
import static sample.cafe.domain.product.ProductType.HANDMADE;

@SpringBootTest
class OrderServiceTest {
    
    @Autowired
    private OrderService orderService;
    
    
    @Autowired
    private ProductRepository productRepository;
    
    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder(){
        //given
        Product product1 = getProduct("001", HANDMADE, SELLING, "아메리카노", 1000);
        Product product2 = getProduct("002", HANDMADE, HOLD, "카페라떼",3000);
        Product product3 = getProduct("003", HANDMADE, STOP_SELLING, "팥빙수",5000);
        productRepository.saveAll(List.of(product1, product2, product3));
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(List.of("001","002"));
        //when
        OrderResponse order = orderService.createOrder(orderCreateRequest);
        //then
        assertThat(order).isNotNull();
        assertThat(order)
                .extracting("registeredDateTime","totalPrice")
                .contains(LocalDateTime.now(),4000);
        assertThat(order.getProducts()).hasSize(2)
                .extracting("productNumber","price")
                .containsExactlyInAnyOrder(
                       tuple("001",1000),
                       tuple("002",3000)
                );
    
    }
    
    private Product getProduct(String productNumber, ProductType productType, ProductSellingStatus sellingStatus,String name,int price) {
        return new Product(productNumber, productType, sellingStatus, name, price);
    }
    
}