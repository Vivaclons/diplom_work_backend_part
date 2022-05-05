package kz.spring.clientservice.service.impl;

import kz.spring.clientservice.model.Customer;
import java.util.List;

public interface ICustomerService {
    List<Customer> searchCustomerByCustomerName(String customerName);
    Customer removeDoctor(Long customerId, Long doctorId);
    Customer addDoctor(Long customerId, Long doctorId);
    Customer updateDoctor(Long customerId, Long doctorId);
    Customer getById(Long id);
    Customer getByCustomerEmail(String email);
    Customer getByCustomerName(String customerName);
    void update(Customer customer);
    List<Customer> getAllCustomer();
    void DeleteById(Long id);
    void rating(Long customerId, double rating);
    boolean checkDoctor(Customer customer, Long doctorId);
    Customer findCustomerUsername(String username);
}
