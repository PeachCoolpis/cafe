package sample.cafe.unit;

import lombok.Getter;
import sample.cafe.unit.beverage.Beverage;
import sample.cafe.unit.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
public class CafeKiosk {
    
    private List<Beverage> beverages;
    
    public CafeKiosk() {
        this.beverages = new ArrayList<Beverage>();
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
        return new Order(LocalDateTime.now(), beverages);
    }
    
    public int size() {
        return this.beverages.size();
    }
    
    public void clear() {
        this.beverages.clear();
    }
}
