package model;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Customer {
    private static int counter = 0;
    private final int id;
    private String name;
    private String sector;
    private final Date registrationDate;
    private final List<Order> orders = new ArrayList<>();

    public Customer(String name, String sector, Date registrationDate) {
        this.id = ++counter;
        this.name = name;
        this.sector = sector;
        this.registrationDate = registrationDate;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
