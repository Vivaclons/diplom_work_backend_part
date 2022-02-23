package kz.spring.authentication.service;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.repository.CustomerRepository;
import kz.spring.authentication.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService, UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Customer getById(Long customerId) {
        return customerRepository.getById(customerId);
    }

    @Override
    public Customer getByCustomerName(String customerName) {
        return customerRepository.getByCustomerName(customerName);
    }

    @Override
    public Boolean isAdmin(String customerName, String pass) {
        Customer customer = customerRepository.getByCustomerName(customerName);
        if(customer == null){
            return false;
        }
        return customer.getPassword().equals(pass) && customer.isAdmin();
    }

    @Override
    public Boolean isDoctor(String customerName, String pass) {
        return null;
    }

    @Override
    public Boolean isMedCenter(String customerName, String pass) {
        return null;
    }

    @Override
    public Boolean isCustomer(String customerName, String pass) {
        Customer customer = customerRepository.getByCustomerName(customerName);
        if(customer == null){
            return false;
        }
        return customer.getPassword().equals(pass);
    }

    @Override
    public void update(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<Customer> getAllCustomersBy() {
        return customerRepository.getCustomersBy();
    }

    @Override
    public void DeleteByID(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public boolean checkByLoginAndPassword(String login, String password) {
        return customerRepository.existsCustomerByUsernameAndPassword(login, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepository.findAll();
        Customer customer = null;

        for (Customer customer1 : customers){
            if(customer1.getCustomerName().equals(username)){
                customer = customer1;
            }
        }

        if(customer == null){
            throw new UsernameNotFoundException("Authentication error with user name(Not found user): " + username);
        }
        return customer;
    }
}
