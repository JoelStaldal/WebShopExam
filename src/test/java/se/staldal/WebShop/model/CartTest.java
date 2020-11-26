package se.staldal.WebShop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {

    Cart cart;
    Product product1;
    Product product2;
    Product product3;

    @BeforeEach
    void setup() {
        cart = new Cart();
        product1 = new Product(1L,"product1", 10, new Category("category1"));
        product2 = new Product(2L,"product2", 100, new Category("category2"));
        product3 = new Product(3L,"product3", 500, new Category("category3"));
        cart.addItem(product1);
        cart.addItem(product2);
        cart.addItem(product3);
    }

    @Test
    void testGetItems() {
        List<OrderItem> items = cart.getItems();

        Optional<OrderItem> item1 = cart.getItem(product1);
        Optional<OrderItem> item2 = cart.getItem(product2);
        Optional<OrderItem> item3 = cart.getItem(product3);

        assertThat(items, contains(item1.get(), item2.get(), item3.get()));
        assertThat(items, hasSize(3));
    }

    @Test
    void testGetExistingItem() {
        Optional<OrderItem> item = cart.getItem(product1);
        Product actualProduct = item.get().getProduct();
        assertEquals(product1, actualProduct);
    }

    @Test
    void testGetNonExistingItem() {
        Optional<OrderItem> actualItem = cart.getItem(new Product());
        assertEquals(Optional.empty(), actualItem);
    }

    @Test
    void testAddItemWithNewProduct() {
        Product product4 = new Product(4L,"product4", 1000, new Category("category4"));
        cart.addItem(product4);

        List<OrderItem> items = cart.getItems();
        assertThat(items, hasSize(4));
        
        Optional<OrderItem> item4 = cart.getItem(product4);
        Product actualProduct = item4.get().getProduct();
        assertEquals(product4, actualProduct);

        double actualTotal = cart.getTotal();
        double expTotal = 1610;
        assertEquals(expTotal, actualTotal);
    }

    @Test
    void testAddItemWithExistingProduct() {
        cart.addItem(product1);

        List<OrderItem> items = cart.getItems();
        assertThat(items, hasSize(3));

        Optional<OrderItem> item = cart.getItem(product1);
        int actualQuantity = item.get().getQuantity();
        int expQuantity = 2;
        assertEquals(expQuantity, actualQuantity);

        double actualSubtotal = item.get().getSubtotal();
        double expSubtotal = 20;
        assertEquals(expSubtotal, actualSubtotal);

        double actualTotal = cart.getTotal();
        double expResult = 620;
        assertEquals(expResult, actualTotal);
    }

    @Test
    void testRemoveItem() {
        cart.removeItem(product1);
        List<OrderItem> items = cart.getItems();
        assertThat(items, hasSize(2));
        Optional<OrderItem> item = cart.getItem(product1);
        assertEquals(Optional.empty(), item);
    }

    @Test
    void testGetTotal() {
        double actualTotal = cart.getTotal();
        double expResult = 610;
        assertEquals(expResult, actualTotal);
    }

    @Test
    void testUpdateItemQuantity() {
        cart.updateItemQuantity(product1, 3);
        Optional<OrderItem> item = cart.getItem(product1);
        int actualQuantity = item.get().getQuantity();
        int expQuantity = 3;
        assertEquals(expQuantity, actualQuantity);

        double actualTotal = cart.getTotal();
        double expResult = 630;
        assertEquals(expResult, actualTotal);
    }
}