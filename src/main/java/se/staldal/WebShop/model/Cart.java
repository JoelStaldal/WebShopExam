package se.staldal.WebShop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private final List<OrderItem> items;
    private double total;
    private String size;

    public Cart() {
        items = new ArrayList<>();
        total = 0;
    }

    public String getSize() {
        size = String.valueOf(items.size());
        return size;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Optional<OrderItem> getItem(Product product) {
        return items.stream()
                .filter(i -> i.getProduct().getId() == product.getId())
                .findFirst();
    }

    public void addItem(Product product) {
        Optional<OrderItem> item = getItem(product);

        if(item.isPresent()) {
            int quantity = item.get().getQuantity();
            item.get().setQuantity(++quantity);
        } else {
            items.add(new OrderItem(product));
        }
    }

    public void removeItem(Product product) {
        Optional<OrderItem> item = getItem(product);
        if(item.isPresent()) {
            items.remove(item.get());
        }
    }

    public double getTotal() {
        total = 0;
        for(OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void updateItemQuantity(Product product, int quantity) {
        Optional<OrderItem> item = getItem(product);
        if(item.isPresent()) {
            item.get().setQuantity(quantity);
        }
    }
}
