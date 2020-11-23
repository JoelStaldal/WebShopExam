package se.staldal.WebShop.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ConfirmationMail {

    private String orderId;
    private String orderDate;
    private String customer;

    public ConfirmationMail(String orderId, String orderDate, String customer) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ConfirmationMail{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customer='" + customer + '\'' +
                '}';
    }
}
