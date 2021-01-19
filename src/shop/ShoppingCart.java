package shop;

import shop.discount.DiscountContext;
import shop.history.HistoryStack;
import shop.history.HistoryState;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ShoppingCart {

    final ArrayList<ShoppingCartItem> items = new ArrayList<>();
    DiscountContext discountContext = new DiscountContext(items);

    public void addCartItem(ShoppingCartItem item){
        items.add(item);
        HistoryStack.addHistoryState(new HistoryState(() -> {
            System.out.println("Removing: " + item.product().name());
            items.remove(item);
        }, () -> {
            System.out.println("Adding back: " + item.product().name());
            items.add(item);
        }));
    }


    public static BigDecimal calculatePrice(ArrayList<ShoppingCartItem> items){
        var sum = BigDecimal.ZERO;

        for (var item: items) {
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
        }
        return sum;
    }


    public String receipt() {
        if (discountContext.getMostProfitDiscount() != null) {
            return discountContext.receipt();
        } else {
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
            sb.append(String.format("%28s% 8.2f", "TOTAL:", calculatePrice(items)));
            return sb.toString();
        }
    }


}
