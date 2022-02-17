package kz.spring.clientservice.service.impl;

import kz.spring.clientservice.model.Customer;
import java.util.List;

public interface ICustomerService {
    Customer getById(Long id);
    Customer getByCustomerEmail(String email);
    Customer getByCustomerName(String customerName);
    void update(Customer customer);
    List<Customer> getAllCustomer();
    void DeleteById(Long id);
}
