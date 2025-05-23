package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        cart.printCart();

        cart.searchByID(3);
        cart.searchByTitle("Gotoubun no Hanayome");
        cart.searchByTitle("Oregairu");

    }
}
