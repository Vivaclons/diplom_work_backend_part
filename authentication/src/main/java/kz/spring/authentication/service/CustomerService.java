package kz.spring.authentication.service;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Role;
import kz.spring.authentication.repository.CustomerRepository;
import kz.spring.authentication.service.impl.ICustomerService;
import kz.spring.authentication.service.impl.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements ICustomerService, UserDetailsService, IRoleService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MailDelivery mailDelivery;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer getById(Long customerId) {
        return customerRepository.getById(customerId);
    }

    @Override
    public Customer getByCustomerName(String customerName) {
        return customerRepository.getByCustomerName(customerName);
    }

//    @Override
//    public Role saveRole(Role role){
//        return roleRepository.save(role);
//    }

    @Override
    public void addRoleToCustomer(Customer customer, String roleName){
        Customer customer1 = customerRepository.findByUsername(customer.getUsername());
//        Role role = roleRepository.findByName(roleName);
        if(roleName.equals(Role.USER)){
            customer1.getRoles().add(Role.USER);
        }else{
            customer1.getRoles().add(Role.ADMIN);
        }
    }

//    @Override
//    public List<Role> getRoles(){
//        return roleRepository.findAll();
//    }

//
//    @Override
//    public Boolean isAdmin(String customerName, String pass) {
//        Customer customer = customerRepository.getByCustomerName(customerName);
//        if(customer == null){
//            return false;
//        }
//        return customer.getPassword().equals(pass) && customer.isAdmin();
//    }

//    @Override
//    public Boolean isDoctor(String customerName, String pass) {
//        return null;
//    }
//
//    @Override
//    public Boolean isMedCenter(String customerName, String pass) {
//        return null;
//    }
//
//    @Override
//    public Boolean isCustomer(String customerName, String pass) {
//        Customer customer = customerRepository.getByCustomerName(customerName);
//        if(customer == null){
//            return false;
//        }
//        return customer.getPassword().equals(pass);
//    }

    @Override
    public void update(Customer customer) {
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

    @Override
    public boolean checkByEmailAndPassword(String username, String password) {
        return customerRepository.existsCustomerByUsernameAndPassword(username, password);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUsername(username);

        if(customer == null){
            throw new UsernameNotFoundException("User by this email: " + username + " not found!");
        }
//
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        customer.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        });

//        return new User(customer.getUsername(), customer.getPassword(), authorities);

        return null;
    }

    @Override
    public boolean addUser(Customer customer){
        Customer customer1 = customerRepository.findByUsername(customer.getUsername());

        if(customer1 != null){
            System.out.println("ERROR");
            return false;
        }

        customer.setStatus(true);
        customer.setRoles(Collections.singleton(Role.USER));
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setActivationCode(UUID.randomUUID().toString());

        if(!StringUtils.isEmpty(customer.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8084/registration/activate/%s",
                    customer.getUsername(),
                    customer.getActivationCode()
            );
            mailDelivery.send(customer.getEmail(), "Activation code", message);
        }

        customerRepository.saveAndFlush(customer);
        return true;
    }

    public boolean activateCustomer(String code){

        Customer customer = customerRepository.findByActivationCode(code);

        if(customer == null){
            return false;
        }

        customer.setActivationCode(null);
        customerRepository.saveAndFlush(customer);

        return true;
    }
}
