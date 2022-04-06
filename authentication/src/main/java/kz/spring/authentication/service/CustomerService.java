package kz.spring.authentication.service;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.model.MedCenter;
import kz.spring.authentication.model.Role;
import kz.spring.authentication.repository.CustomerRepository;
import kz.spring.authentication.repository.DoctorRepository;
import kz.spring.authentication.repository.MedCenterRepository;
import kz.spring.authentication.service.impl.ICustomerService;
import kz.spring.authentication.service.impl.IDoctorService;
import kz.spring.authentication.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CustomerService implements ICustomerService, UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MailDelivery mailDelivery;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer getByCustomerName(String customerName) {
        return customerRepository.getByCustomerName(customerName);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.getById(customerId);
    }

    @Override
    public void updateCus(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
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
        Customer customer1 = customerRepository.findByUsername(customer.getUsername());

        if(customer1 != null){
            System.out.println("ERROR");
            return false;
        }

        customer.setStatus(true);

        System.out.println(customer.getRoles());

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setActivationCode(UUID.randomUUID().toString());
        customer.setPeopleCount(50);
        customer.setRating(5.0);
        if(!StringUtils.isEmpty(customer.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/activate/%s",
                    customer.getUsername(),
                    customer.getActivationCode()
            );
            mailDelivery.send(customer.getEmail(), "Activation code", message);
        }

        customerRepository.saveAndFlush(customer);
        return true;
    }

    @Override
    public boolean activateCustomer(String code){

        Customer customer = customerRepository.findByActivationCode(code);

        if(customer == null){
            return false;
        }

        customer.setActivationCode(null);
        customerRepository.saveAndFlush(customer);

        return true;
    }

    @Override
    public String forgetPassword(String email){

        String message = "";

        Customer customer = customerRepository.findByEmail(email);

        if(customer != null){
            System.out.println("ERROR");
            return "ERROR with change password";
        }

        customer.setActivationCode("forget password");

        if(!StringUtils.isEmpty(customer.getEmail())){
            message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/activate/%s",
                    customer.getUsername(),
                    customer.getActivationCode()
            );
            mailDelivery.send(customer.getEmail(), "Activation code", message);
        }

        customerRepository.saveAndFlush(customer);

        return message;

    }

    @Override
    public void updatePassword(String email, String password){

        Customer customer = customerRepository.findByEmail(email);

        if(customer != null){
            System.out.println("ERROR");
        }

        customer.setPassword(passwordEncoder.encode(password));

        customerRepository.saveAndFlush(customer);
    }
}
