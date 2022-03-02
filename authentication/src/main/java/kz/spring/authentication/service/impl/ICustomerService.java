package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.Customer;

import java.util.List;

public interface ICustomerService {

    Customer getCustomerById(Long customerId);

    Customer getByCustomerName(String customerName);

    void updateCus(Customer customer);

    List<Customer> getAllCustomersBy();

    void DeleteByID(Long customerId);

    boolean addUser(Customer customer);

    boolean activateCustomer(String code);
}
