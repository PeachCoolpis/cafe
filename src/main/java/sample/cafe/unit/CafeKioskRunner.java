package sample.cafe.unit;

import sample.cafe.unit.beverage.Americano;
import sample.cafe.unit.beverage.Latte;

public class CafeKioskRunner {
    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk();
        
        cafeKiosk.add(new Americano());
        
        cafeKiosk.add(new Latte());
        
        System.out.println(cafeKiosk.calculateTotalPrice());
    }
}
