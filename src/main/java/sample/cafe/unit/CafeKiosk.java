package sample.cafe.unit;

import lombok.Getter;
import sample.cafe.unit.beverage.Beverage;
import sample.cafe.unit.order.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
public class CafeKiosk {
    
    private static final LocalTime SHOP_OPEN_TILE = LocalTime.of(10, 0);
    private static final LocalTime SHOP_CLOSE_TILE = LocalTime.of(22, 0);
    
    
    
    private List<Beverage> beverages;
    
    public CafeKiosk() {
        this.beverages = new ArrayList<Beverage>();
    }
    
    public void add(Beverage beverage, int count) {
        if (count <= 0 ) {
            throw new IllegalArgumentException("음료는 1잔 이상 주문하실 수 있습니다");
        }
        for (int i = 0; i <count ; i++) {
            this.beverages.add(beverage);
        }
    }
    
    public void add(Beverage beverage) {
            this.beverages.add(beverage);
    }
    
    public void remove(Beverage beverage) {
        this.beverages.remove(beverage);
    }
    
    public int calculateTotalPrice() {
        return this.beverages.stream()
                .mapToInt(Beverage::getPrice)
                .sum();
    }
    
    public Order createOrder() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime localTime = now.toLocalTime();
        if (localTime.isBefore(SHOP_OPEN_TILE) || localTime.isAfter(SHOP_CLOSE_TILE)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요");
        }
        return new Order(now, beverages);
    }
    
    public Order createOrder(LocalDateTime localDateTime) {
        LocalTime localTime = localDateTime.toLocalTime();
        if (localTime.isBefore(SHOP_OPEN_TILE) || localTime.isAfter(SHOP_CLOSE_TILE)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요");
        }
        return new Order(localDateTime, beverages);
    }
    
    public int size() {
        return this.beverages.size();
    }
    
    public void clear() {
        this.beverages.clear();
    }
}
