package kz.spring.clientservice.service;

import kz.spring.clientservice.model.Customer;
import kz.spring.clientservice.repository.CustomerRepository;
import kz.spring.clientservice.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getById(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public Customer getByCustomerName(String customerName) {
        return customerRepository.getCustomerName(customerName);
    }

    @Override
    public Customer getByCustomerEmail(String email){
        return customerRepository.getByCustomerEmail(email);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<Customer> getAllCustomerBy() {
        return customerRepository.getCustomerBy();
    }

    @Override
    public void DeleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
