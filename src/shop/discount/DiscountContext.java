package shop.discount;

import shop.ShoppingCart;
import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DiscountContext {

    DecimalFormat decimalFormat = new DecimalFormat("0.###");

    ArrayList<DiscountStrategy> discounts = new ArrayList<>();
    ArrayList<ShoppingCartItem> items;

    public DiscountContext(ArrayList<ShoppingCartItem> items) {
        this.items = items;
    }

    public DiscountStrategy getMostProfitDiscount() {
        DiscountStrategy mostProfitDiscount = null;

        for (DiscountStrategy discountStrategy : discounts) {
            if (mostProfitDiscount == null) {
                mostProfitDiscount = discountStrategy;
            }
            if (mostProfitDiscount.calculatePrice(items).doubleValue() > discountStrategy.calculatePrice(items).doubleValue()) {
                mostProfitDiscount = discountStrategy;
            }
        }
        return mostProfitDiscount;
    }


    public String receipt() {
        String line = "-------------------------------------\n";
        StringBuilder sb = new StringBuilder();
        sb.append(line);
        var list = items.stream()
                .sorted(Comparator.comparing(item -> item.product().name()))
                .collect(Collectors.toList());
        for (var each : list) {
            sb.append(String.format("%s %-24s % 7.2f \n", each.quantity() + "x", each.product().name(), each.itemCost().multiply(new BigDecimal(each.quantity()))));
        }
        sb.append(line);
        sb.append(String.format("%28s% 8.2f \n", "TOTAL:", ShoppingCart.calculatePrice(items)));

        sb.append(String.format("%20s%s \n", "", getMostProfitDiscount().getDiscountName() + ": -" + decimalFormat.format(ShoppingCart.calculatePrice(items).subtract(getMostProfitDiscount().calculatePrice(items)).doubleValue()) + "kr")) ;
        sb.append(String.format("%28s% 8.2f", "To pay:", getMostProfitDiscount().calculatePrice(items)));
        return sb.toString();
    }




    public void addDiscount(DiscountStrategy discountStrategy) {
        discounts.add(discountStrategy);
    }
}
