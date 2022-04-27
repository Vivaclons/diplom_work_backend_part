package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.CustomerKz;
import kz.spring.authentication.model.CustomerRu;

import java.util.List;

public interface ICustomerService {

    Customer getCustomerById(Long customerId);

    Customer getByCustomerName(String customerName);

    void updateCus(Customer customer);

    List<Customer> getAllCustomersBy();

    void DeleteByID(Long customerId);

    boolean addUser(Customer customer);

    boolean activateCustomer(String code);

    String forgetPassword(String email);

    void updatePassword(String email, String password);

    void updateCusKz(CustomerKz customerKz);

    void updateCusRu(CustomerRu customerRu);

    CustomerKz getByCustomerKzName(String customerName);

    CustomerRu getByCustomerRuName(String customerName);

    CustomerKz getCustomerKzById(String username);

    CustomerRu getCustomerRuById(String username);
}
