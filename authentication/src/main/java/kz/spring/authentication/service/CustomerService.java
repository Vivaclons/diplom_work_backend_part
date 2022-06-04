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
    private DoctorRepository doctorRepository;

    @Autowired
    private MedCenterRepository medCenterRepository;

    @Autowired
    private MailDelivery mailDelivery;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(email);

        Doctor doctor = doctorRepository.findDoctorsByDoctorEmail(email);

        MedCenter medCenter = medCenterRepository.findMedCenterByMedCenterEmail(email);
        if(customer != null){

            if(customer == null){
                throw new UsernameNotFoundException("User by this email: " + email + " not found!");
            }

//            if(customer.getActivationCode() != null){
//                throw new UsernameNotFoundException("User by this email: " + email + " not activated!");
//            }

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            customer.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.toString()));
            });

            return new User(customer.getEmail(), customer.getPassword(), authorities);
        } else if(doctor != null){
            if(doctor == null){
                throw new UsernameNotFoundException("Doctor by this email: " + email + " not found!");
            }

//            if(doctor.getActivationCode() != null){
//                throw new UsernameNotFoundException("Doctor by this email: " + email + " not activated!");
//            }

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            doctor.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.toString()));
            });

            return new User(doctor.getDoctorEmail(), doctor.getPassword(), authorities);
        } else{
            if(medCenter == null){
                throw new UsernameNotFoundException("medCenter by this email: " + email + " not found!");
            }

//            if(medCenter.getActivationCode() != null){
//                throw new UsernameNotFoundException("medCenter by this email: " + email + " not activated!");
//            }

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            medCenter.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.toString()));
            });

            return new User(medCenter.getMedCenterEmail(), medCenter.getPassword(), authorities);
        }

    }

    @Override
    public boolean addUser(Customer customer){

        Customer customer1 = customerRepository.findCustomerByEmail(customer.getEmail());

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

        if(customer == null && customer != null){
            return false;
        }

        customer.setActivationCode(null);
        customerRepository.saveAndFlush(customer);

        return true;
    }

    @Override
    public String forgetPassword(String email){

        String message = "";

        Customer customer = customerRepository.findCustomerByEmail(email);

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

        Customer customer = customerRepository.findCustomerByEmail(email);
        if(customer != null){
            System.out.println("ERROR");
        }

        customer.setPassword(passwordEncoder.encode(password));

        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void update(Customer customer, String email) {

        Customer updateCustomer = customerRepository.findCustomerByEmail(email);

        if(updateCustomer != null){

            if(customer.getCustomerName() != null){
                updateCustomer.setCustomerName(customer.getCustomerName());
            }
            if(customer.getCustomerSurname() != null){
                updateCustomer.setCustomerSurname(customer.getCustomerSurname());
            }
            if(customer.getAddress() != null){
                updateCustomer.setAddress(customer.getAddress());
            }
            if(customer.getCustomerTelNumber() != null){
                updateCustomer.setCustomerTelNumber(customer.getCustomerTelNumber());
            }
            if(customer.getEmail() != null){
                updateCustomer.setEmail(customer.getEmail());
            }
            if(customer.getUsername() != null){
                updateCustomer.setUsername(customer.getUsername());
            }
            customerRepository.saveAndFlush(updateCustomer);
        }else{
            System.out.println("Customer is empty!");
        }
    }

    @Override
    public Customer getCustomerByEmail(String email){
        Customer customer = customerRepository.findCustomerByEmail(email);
        if(customer == null){
            System.out.println("USER NOT FOUND!");
        }
        return customer;
    }
}
