package service;

import model.Customer;
import model.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceService {

    private final List<Invoice> invoices = new ArrayList<>();

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoices;
    }

    public List<Invoice> getInvoicesAboveAmount(double amount) {
        return invoices.stream()
                .filter(invoice -> invoice.getAmount() > amount)
                .collect(Collectors.toList());
    }

    public double getAverageOfInvoicesAboveAmount(double amount) {
        return invoices.stream()
                .filter(invoice -> invoice.getAmount() > amount)
                .mapToDouble(Invoice::getAmount)
                .average()
                .orElse(0);
    }

    public List<String> getCustomerNamesWithInvoicesBelowAmount(double amount, List<Customer> customers) {
        return customers.stream()
                .filter(customer -> customer.getOrders().stream()
                        .flatMap(order -> order.getInvoices().stream())
                        .anyMatch(invoice -> invoice.getAmount() < amount))
                .map(Customer::getName)
                .collect(Collectors.toList());
    }
}
