package sample.cafe.unit;

import org.junit.jupiter.api.Test;
import sample.cafe.unit.beverage.Americano;
import sample.cafe.unit.beverage.Latte;
import sample.cafe.unit.order.Order;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CafeKioskTest {
    
    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano(),1);
        cafeKiosk.add(new Latte(),1);
        
        assertThat(cafeKiosk.getBeverages()).hasSize(2);
    }
    
    @Test
    void addSerialBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano,2);
        
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);
    }
    
    
    @Test
    void addZeroBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문하실 수 있습니다");
        
    }
    
    
    @Test
    void createOderTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        LocalDateTime localDateTime = LocalDateTime.of(2025, Month.FEBRUARY,23,23,10);
        assertThatThrownBy(() -> cafeKiosk.createOrder(localDateTime))
              .isExactlyInstanceOf(IllegalArgumentException.class)
              .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요");
      
    }
    
    @Test
        void createOderTime2() {
            CafeKiosk cafeKiosk = new CafeKiosk();
            cafeKiosk.add(new Americano());
            LocalDateTime localDateTime = LocalDateTime.of(2025, Month.FEBRUARY,23,22,0);
            cafeKiosk.createOrder(localDateTime);
            assertThat(cafeKiosk.getBeverages()).hasSize(1);
    }
    
    
    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        
        cafeKiosk.add(americano,1);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        
        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }
    
    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();
        
        cafeKiosk.add(americano,1);
        cafeKiosk.add(latte,1);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);
        
        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }
    
    
}