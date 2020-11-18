package se.staldal.WebShop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<OrderItem> items;
    private double total;

    public Cart() {
        items = new ArrayList<>();
        total = 0;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderItem getItem(Product product) {
        for (OrderItem item : items){
            if(item.getProduct().getId() == product.getId()){
                return item;
            }
        }
        return null;
    }

    public void addItem(Product product) {
        OrderItem item = new OrderItem(product);
        items.add(item);
    }

    public void removeItem(Product product) {
        OrderItem item = getItem(product);
        items.remove(item);
    }

    public double getTotal() {
        total = 0;
        for(OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void updateItem(Product product, int quantity) {
        OrderItem item = getItem(product);
        item.setQuantity(quantity);
    }
}
