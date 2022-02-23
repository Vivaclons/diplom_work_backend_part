package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.Customer;

import java.util.List;

public interface ICustomerService {

    Customer getById(Long customerId);

    Customer getByCustomerName(String customerName);

    Boolean isAdmin(String customerName, String pass);

    Boolean isDoctor(String customerName, String pass);

    Boolean isMedCenter(String customerName, String pass);

    Boolean isCustomer(String customerName, String pass);

    void update(Customer customer);

    List<Customer> getAllCustomersBy();

    void DeleteByID(Long customerId);

    boolean checkByLoginAndPassword(String login, String password);
}
