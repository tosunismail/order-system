package service;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}
