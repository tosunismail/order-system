package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Order {

    private static int counter = 0;
    private final int id;
    private final Date date;
    private final List<Invoice> invoices = new ArrayList<>();

    public Order(Date date) {
        this.id = ++counter;
        this.date = date;
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }
}
