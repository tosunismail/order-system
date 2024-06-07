import model.Customer;
import model.Invoice;
import model.Order;
import service.CustomerService;
import service.InvoiceService;
import service.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws ParseException {

        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        InvoiceService invoiceService = new InvoiceService();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Customer customer1 = new Customer("Ali", "Technology", dateFormat.parse("15/06/2023"));
        Customer customer2 = new Customer("Can", "Health", dateFormat.parse("12/05/2024"));
        Customer customer3 = new Customer("Cem", "Education", dateFormat.parse("10/06/2023"));
        Customer customer4 = new Customer("Deniz", "Technology", dateFormat.parse("05/05/2024"));
        Customer customer5 = new Customer("Ela", "Health", dateFormat.parse("03/06/2023"));

        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);
        customerService.addCustomer(customer3);
        customerService.addCustomer(customer4);
        customerService.addCustomer(customer5);

        Order order1 = new Order(dateFormat.parse("20/06/2023"));
        Order order2 = new Order(dateFormat.parse("21/06/2023"));
        Order order3 = new Order(dateFormat.parse("22/06/2023"));

        Invoice invoice1 = new Invoice(1000);
        Invoice invoice2 = new Invoice(2000);
        Invoice invoice3 = new Invoice(3000);
        Invoice invoice4 = new Invoice(400);
        Invoice invoice5 = new Invoice(500);

        order1.addInvoice(invoice1);
        order1.addInvoice(invoice2);

        order2.addInvoice(invoice3);
        order2.addInvoice(invoice4);

        order3.addInvoice(invoice5);

        customer1.addOrder(order1);
        customer2.addOrder(order2);
        customer3.addOrder(order3);

        orderService.addOrder(order1);
        orderService.addOrder(order2);
        orderService.addOrder(order3);

        invoiceService.addInvoice(invoice1);
        invoiceService.addInvoice(invoice2);
        invoiceService.addInvoice(invoice3);
        invoiceService.addInvoice(invoice4);
        invoiceService.addInvoice(invoice5);

        // Tüm müşterileri listele
        System.out.println("Tüm Müşteriler:");
        customerService.getAllCustomers().forEach(System.out::println);

        // İçerisinde 'C' harfi olan müşterileri listele
        System.out.println("\nAdında 'C' harfi olan Müşteriler:");
        customerService.getCustomersWithNameContaining('C').forEach(System.out::println);

        // Haziran ayında kayıt olan müşterilerin faturalarının toplam tutarını listele
        System.out.println("\nHaziran ayında kayıt olan müşterilerin faturalarının toplam tutarı:");
        double totalInvoiceAmountInJune = customerService.getCustomersRegisteredInMonth(Calendar.JUNE).stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getInvoices().stream())
                .mapToDouble(Invoice::getAmount)
                .sum();
        System.out.println(totalInvoiceAmountInJune);

        // Sistemdeki tüm faturaları listele
        System.out.println("\nTüm Faturalar:");
        invoiceService.getAllInvoices().forEach(System.out::println);

        // Sistemdeki 1500TL üstündeki faturaları listele
        System.out.println("\n1500TL üzerindeki faturalar:");
        invoiceService.getInvoicesAboveAmount(1500).forEach(System.out::println);

        // Sistemdeki 1500TL üstündeki faturaların ortalamasını hesapla
        System.out.println("\n1500TL üzerindeki faturaların ortalaması:");
        double averageInvoiceAbove1500 = invoiceService.getAverageOfInvoicesAboveAmount(1500);
        System.out.println(averageInvoiceAbove1500);

        // Sistemdeki 500TL altındaki faturalara sahip müşterilerin isimlerini listele
        System.out.println("\n500TL altındaki faturalara sahip müşterilerin isimleri:");
        invoiceService.getCustomerNamesWithInvoicesBelowAmount(500, customerService.getAllCustomers()).forEach(System.out::println);

        // Haziran ayının faturalarının ortalaması 750TL altı olan firmaların hangi sektörde olduğunu listele
        System.out.println("\nHaziran ayının, fatura ortalaması 750TL altı olan firmaların sektörü:");
        customerService.getCustomersRegisteredInMonth(Calendar.JUNE).stream()
                .filter(customer -> {
                    double averageInvoice = customer.getOrders().stream()
                            .flatMap(order -> order.getInvoices().stream())
                            .mapToDouble(Invoice::getAmount)
                            .average()
                            .orElse(0);
                    return averageInvoice < 750;
                })
                .map(Customer::getSector)
                .distinct()
                .forEach(System.out::println);
    }
}
