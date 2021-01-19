package shop.discount;

import shop.ShoppingCart;
import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ThreeForTwo implements DiscountStrategy {

    private String discountName = "Three for two";

    @Override
    public String getDiscountName() {
        return discountName;
    }



    @Override
    public BigDecimal calculatePrice(ArrayList<ShoppingCartItem> items){
        var sum = BigDecimal.ZERO;

        for (var item: items) {
            if (item.quantity() > 2) {
                sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum).subtract(item.itemCost());
            } else {
                sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
            }
        }
        return sum;
    }

}
