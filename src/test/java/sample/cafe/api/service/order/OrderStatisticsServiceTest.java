package sample.cafe.api.service.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sample.cafe.api.client.MailSendClient;
import sample.cafe.domain.history.mail.MailSendHistory;
import sample.cafe.domain.history.mail.MailSendHistoryRepository;
import sample.cafe.domain.order.Order;
import sample.cafe.domain.order.OrderRepository;
import sample.cafe.domain.order.OrderStatus;
import sample.cafe.domain.product.Product;
import sample.cafe.domain.product.ProductRepository;
import sample.cafe.domain.product.ProductSellingStatus;
import sample.cafe.domain.product.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static sample.cafe.domain.product.ProductType.*;


@SpringBootTest
class OrderStatisticsServiceTest {

    @Autowired
    private OrderStatisticsService orderStatisticsService;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    MailSendHistoryRepository mailSendHistoryRepository;
    
    @MockBean
    private MailSendClient mailSendClient;
    
    
    
    @AfterEach
    void testDown() {
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
    }
    
    @DisplayName("결제완료 주문들을 조회하여 매출 통계 메일을 전송한다.")
    @Test
    void sendOrderStatisticsMail(){
        //given
        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 2000);
        Product product3 = createProduct(HANDMADE, "003", 3000);
        productRepository.saveAll(List.of(product1, product2, product3));
        //when
        LocalDateTime orderTime = LocalDateTime.of(2023, 3, 5, 00, 0);
        List<Product> products = List.of(product1, product2, product3);
        
        Order order1 = createPaymentComplateOrder(products, LocalDateTime.of(2023,3,4,23,59));
        Order order2 = createPaymentComplateOrder(products, orderTime);
        Order order3 = createPaymentComplateOrder(products, LocalDateTime.of(2023,3,5,0,0));
        
        // mock 객체를 넣어놓는다.
        Mockito.when(mailSendClient.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(true);
        
        //then
        
        boolean result = orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2023, 3, 5), "rlglxh@naver.com");
        
        Assertions.assertThat(result).isTrue();
        List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
        assertThat(histories).hasSize(1)
                .extracting("content")
                .contains("총 매출 합계는 12000원입니다.");
    }
    
    private static Order createPaymentComplateOrder(List<Product> products,LocalDateTime orderTime) {
        return Order.builder()
                .products(products)
                .orderStatus(OrderStatus.COMPLETED)
                .registeredDateTime(orderTime)
                .build();
    }
    
    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("메뉴 이름")
                .build();
    }
}