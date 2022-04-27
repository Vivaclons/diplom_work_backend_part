package kz.spring.authentication.service;

import kz.spring.authentication.model.*;
import kz.spring.authentication.repository.*;
import kz.spring.authentication.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class CustomerService implements ICustomerService, UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerKzRepository customerKzRepository;

    @Autowired
    private CustomerRuRepository customerRuRepository;

    @Autowired
    private MailDelivery mailDelivery;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer getByCustomerName(String customerName) {
        return customerRepository.getByCustomerName(customerName);
    }

    @Override
    public CustomerKz getByCustomerKzName(String customerName) {
        return customerKzRepository.getByCustomerName(customerName);
    }

    @Override
    public CustomerRu getByCustomerRuName(String customerName) {
        return customerRuRepository.getByCustomerName(customerName);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.getById(customerId);
    }

    @Override
    public CustomerKz getCustomerKzById(String  username){
        return customerKzRepository.findByUsername(username);
    }

    @Override
    public CustomerRu getCustomerRuById(String username){
        return customerRuRepository.findByUsername(username);
    }

    @Override
    public void updateCus(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void updateCusKz(CustomerKz customerKz){
        customerKz.setPassword(passwordEncoder.encode(customerKz.getPassword()));
        customerKzRepository.saveAndFlush(customerKz);
    }

    @Override
    public void updateCusRu(CustomerRu customerRu){
        customerRu.setPassword(passwordEncoder.encode(customerRu.getPassword()));
        customerRuRepository.saveAndFlush(customerRu);
    }

    @Override
    public List<Customer> getAllCustomersBy() {
        return customerRepository.getCustomersBy();
    }

    @Override
    public void DeleteByID(Long customerId) {
        customerRepository.deleteById(customerId);
        customerRuRepository.deleteById(customerId);
        customerKzRepository.deleteById(customerId);
    }

    //Customer
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUsername(username);

        if(customer == null){
            throw new UsernameNotFoundException("User by this userName: " + username + " not found!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        customer.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        });

        return new User(customer.getUsername(), customer.getPassword(), authorities);
    }

    @Override
    public boolean addUser(Customer customer){
        CustomerKz customerKz1 = new CustomerKz();
        CustomerRu customerRu1 = new CustomerRu();

        Customer customer1 = customerRepository.findByUsername(customer.getUsername());
        CustomerRu customerRu = customerRuRepository.findByUsername(customer.getUsername());
        CustomerKz customerKz = customerKzRepository.findByUsername(customer.getUsername());

        if(customer1 != null && customerKz != null && customerRu != null){
            System.out.println("ERROR");
            return false;
        }

        customerKz1.setUsername(customer.getUsername());
        customerRu1.setUsername(customer.getUsername());

        customer.setStatus(true);
        customerKz1.setStatus(true);
        customerRu1.setStatus(true);

        System.out.println(customer.getRoles());

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerKz1.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRu1.setPassword(passwordEncoder.encode(customer.getPassword()));

        customer.setActivationCode(UUID.randomUUID().toString());
        customerKz1.setActivationCode(UUID.randomUUID().toString());
        customerRu1.setActivationCode(UUID.randomUUID().toString());

        customer.setPeopleCount(50);
        customerRu1.setPeopleCount(50);
        customerKz1.setPeopleCount(50);

        customer.setRating(5.0);
        customerKz1.setRating(5.0);
        customerRu1.setRating(5.0);

        customerKz1.setEmail(customer.getEmail());
        customerRu1.setEmail(customer.getEmail());
        if(!StringUtils.isEmpty(customer.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/activate/%s",
                    customer.getUsername(),
                    customer.getActivationCode()
            );
            mailDelivery.send(customer.getEmail(), "Activation code", message);
        }

        customerRepository.saveAndFlush(customer);
        customerRuRepository.saveAndFlush(customerRu1);
        customerKzRepository.saveAndFlush(customerKz1);

        return true;
    }

    @Override
    public boolean activateCustomer(String code){

        Customer customer = customerRepository.findByActivationCode(code);
        CustomerRu customerRu = customerRuRepository.findByActivationCode(code);
        CustomerKz customerKz = customerKzRepository.findByActivationCode(code);

        if(customer == null && customer != null && customerKz != null && customerRu != null){
            return false;
        }

        customer.setActivationCode(null);
        customerKz.setActivationCode(null);
        customerRu.setActivationCode(null);
        customerRepository.saveAndFlush(customer);
        customerRuRepository.saveAndFlush(customerRu);
        customerKzRepository.saveAndFlush(customerKz);

        return true;
    }

    @Override
    public String forgetPassword(String email){

        String message = "";

        Customer customer = customerRepository.findByEmail(email);
        CustomerRu customerRu = customerRuRepository.findByEmail(email);
        CustomerKz customerKz = customerKzRepository.findByEmail(email);

        if(customer != null){
            System.out.println("ERROR");
            return "ERROR with change password";
        }

        customer.setActivationCode("forget password");
        customerKz.setActivationCode("forget password");
        customerRu.setActivationCode("forget password");

        if(!StringUtils.isEmpty(customer.getEmail())){
            message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/activate/%s",
                    customer.getUsername(),
                    customer.getActivationCode()
            );
            mailDelivery.send(customer.getEmail(), "Activation code", message);
        }

        customerRepository.saveAndFlush(customer);
        customerRuRepository.saveAndFlush(customerRu);
        customerKzRepository.saveAndFlush(customerKz);

        return message;

    }

    @Override
    public void updatePassword(String email, String password){

        Customer customer = customerRepository.findByEmail(email);
        CustomerRu customerRu = customerRuRepository.findByEmail(email);
        CustomerKz customerKz = customerKzRepository.findByEmail(email);
        if(customer != null && customerKz != null && customerRu != null){
            System.out.println("ERROR");
        }

        customer.setPassword(passwordEncoder.encode(password));
        customerKz.setPassword(passwordEncoder.encode(password));
        customerRu.setPassword(passwordEncoder.encode(password));

        customerRepository.saveAndFlush(customer);
        customerKzRepository.saveAndFlush(customerKz);
        customerRuRepository.saveAndFlush(customerRu);
    }
}
