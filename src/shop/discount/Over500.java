package shop.discount;

import shop.ShoppingCart;
import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Over500 implements DiscountStrategy {



        private String discountName = "Over 500";



        @Override
        public String getDiscountName() {
            return discountName;
        }

        @Override
        public BigDecimal calculatePrice(ArrayList<ShoppingCartItem> items){
            var sum = BigDecimal.ZERO;

            for (var item: items) {
                sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
            }

            if (sum.doubleValue() > 500) {
                return sum.multiply(new BigDecimal(0.9));
            } else {
                return sum;
            }
        }

    }
