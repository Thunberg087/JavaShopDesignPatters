package shop.discount;

import shop.ShoppingCartItem;
import java.math.BigDecimal;
import java.util.ArrayList;


public interface DiscountStrategy {

    String getDiscountName();

    BigDecimal calculatePrice(ArrayList<ShoppingCartItem> items);
}



