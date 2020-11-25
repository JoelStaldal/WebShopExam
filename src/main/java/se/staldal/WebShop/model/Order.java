package se.staldal.WebShop.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Instant orderDate;
    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private Status status = Status.ONGOING;
    private double totalPrice;

    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<OrderItem> items;

    public Order() {
    }

    public Order(double totalPrice, User user, List<OrderItem> items) {
        this.totalPrice = totalPrice;
        this.user = user;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

}
