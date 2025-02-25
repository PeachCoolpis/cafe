package sample.cafe.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafe.domain.product.Product;
import sample.cafe.domain.product.ProductRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static sample.cafe.domain.product.ProductSellingStatus.*;
import static sample.cafe.domain.product.ProductType.*;


@SpringBootTest
@ActiveProfiles("test")
class ProductRepositoryTest {
    
    
    @Autowired
    private ProductRepository productRepository;
    
    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void findAllBySellingStatusIn(){
        //given
        Product product1 = new Product("001", HANDMADE, SELLING, "아메리카노",4000);
        Product product2 = new Product("002", HANDMADE, HOLD, "카페라떼",4500);
        Product product3 = new Product("003", HANDMADE, STOP_SELLING, "팥빙수",7000);
        productRepository.saveAll(List.of(product1, product2, product3));
        
        //when
        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));
        
        //then
        assertThat(products).hasSize(2)
                        .extracting("productNumber","name","sellingStatus")
                                .containsExactlyInAnyOrder(
                                        tuple("001","아메리카노",SELLING),
                                        tuple("002","카페라떼",HOLD)
                                );
        products.forEach(product -> assertThat(product.getSellingStatus()).isIn(SELLING, HOLD));
    }
    
    @DisplayName("원하는 판매번호를 가진 상품들을 조회한다.")
    @Test
    void findAllByProductNumberIn(){
        //given
        Product product1 = new Product("001", HANDMADE, SELLING, "아메리카노",4000);
        Product product2 = new Product("002", HANDMADE, HOLD, "카페라떼",4500);
        Product product3 = new Product("003", HANDMADE, STOP_SELLING, "팥빙수",7000);
        productRepository.saveAll(List.of(product1, product2, product3));
        
        //when
        List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));
        
        //then
        assertThat(products).hasSize(2)
                .extracting("productNumber","name","sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001","아메리카노",SELLING),
                        tuple("002","카페라떼",HOLD)
                );
        products.forEach(product -> assertThat(product.getSellingStatus()).isIn(SELLING, HOLD));
    }
}