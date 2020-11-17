package se.staldal.WebShop.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private Status status = Status.ONGOING;
    private double totalPrice;

    @JoinColumn(name = "user_id")
    @ManyToOne
    User users;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<OrderItem> items;

    public Order() {
    }

    public Order(double totalPrice, User users, List<OrderItem> items) {
        this.totalPrice = totalPrice;
        this.users = users;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", users=" + users +
                ", items=" + items +
                '}';
    }
}
