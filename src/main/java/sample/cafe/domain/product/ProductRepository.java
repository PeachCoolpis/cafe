package sample.cafe.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> productSellingType);
    List<Product> findAllByProductNumberIn(List<String> productNumbers);
}
