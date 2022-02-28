package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.Customer;

import java.util.List;

public interface ICustomerService {

    Customer getById(Long customerId);

    Customer getByCustomerName(String customerName);

    void update(Customer customer);

    List<Customer> getAllCustomersBy();

    void DeleteByID(Long customerId);

    boolean checkByEmailAndPassword(String username, String password);

    boolean addUser(Customer customer);

    boolean activateCustomer(String code);
}
