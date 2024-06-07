package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public List<Customer> getCustomersWithNameContaining(char c) {
        return customers.stream()
                .filter(customer -> customer.getName().indexOf(c) != -1)
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomersRegisteredInMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        return customers.stream()
                .filter(customer -> {
                    calendar.setTime(customer.getRegistrationDate());
                    return calendar.get(Calendar.MONTH) == month;
                })
                .collect(Collectors.toList());
    }
}
