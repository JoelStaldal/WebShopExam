package se.staldal.WebShop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<CartItem> items;
    private double total;

    public Cart() {
        items = new ArrayList<>();
        total = 0;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
