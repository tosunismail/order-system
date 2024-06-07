package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Invoice {

    private static int counter = 0;
    private final int id;
    private final double amount;

    public Invoice(double amount) {
        this.id = ++counter;
        this.amount = amount;
    }
}
