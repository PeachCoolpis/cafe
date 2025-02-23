package sample.cafe.product;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String productNumber;
    
    @Enumerated(EnumType.STRING)
    private ProductType type;
    
    @Enumerated(EnumType.STRING)
    private ProductSellingStatus sellingStatus;
    
    private String name;
    
    private int price;
    
}
