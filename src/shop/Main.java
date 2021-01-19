package shop;

import shop.discount.CheapestFree;
import shop.discount.Over500;
import shop.discount.ThreeForTwo;
import shop.history.HistoryStack;

public class Main {
  public static void main(String[] args) {

      ShoppingCart cart = new ShoppingCart();


      cart.discountContext.addDiscount(new Over500());
      cart.discountContext.addDiscount(new CheapestFree());
      cart.discountContext.addDiscount(new ThreeForTwo());


      Product tomato = new Product("Tomat");
      Product korv = new Product("Korv");


      cart.addCartItem(new ShoppingCartItem(korv, 11, 2));
      cart.addCartItem(new ShoppingCartItem(tomato, 10, 2));


      System.out.println(cart.receipt());
      HistoryStack.undo();HistoryStack.undo();HistoryStack.undo();
      System.out.println(cart.receipt());
      HistoryStack.redo();


  }
}
