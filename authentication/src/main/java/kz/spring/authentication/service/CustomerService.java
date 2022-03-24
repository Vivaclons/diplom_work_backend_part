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
public class CustomerService implements ICustomerService, UserDetailsService, IDoctorService, IMedCenterService {

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

    @Override
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.getById(doctorId);
    }

    @Override
    public Doctor getByDoctorName(String doctorName) {
        return doctorRepository.getByDoctorName(doctorName);
    }

    @Override
    public void updateDoc(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public List<Doctor> getAllDoctorsBy() {
        return doctorRepository.getDoctorsBy();
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
    public MedCenter getMedCenterById(Long medCenterId) {
        return medCenterRepository.getById(medCenterId);
    }

    @Override
    public MedCenter getByMedCenterName(String medCenterName) {
        return  medCenterRepository.getByMedCenterName(medCenterName);
    }

    @Override
    public void updateMed(MedCenter medCenter) {
        medCenter.setPassword(passwordEncoder.encode(medCenter.getPassword()));
        medCenterRepository.saveAndFlush(medCenter);
    }

    @Override
    public List<MedCenter> getAllMedCentersBy() {
        return medCenterRepository.getMedCentersBy();
    }

    @Override
    public void DeleteByID(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void DeleteByIDDoc(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public void DeleteByIDMed(Long medCenterId) {
        medCenterRepository.deleteById(medCenterId);
    }

    @Override
    public boolean addMedCenter(MedCenter medCenter) {
        MedCenter medCenter1 = medCenterRepository.findByUsername(medCenter.getUsername());

        if(medCenter1 != null){
            System.out.println("ERROR");
            return false;
        }

        medCenter.setStatus(true);
        medCenter.setPassword(passwordEncoder.encode(medCenter.getPassword()));
        medCenter.setActivationCode(UUID.randomUUID().toString());

        if(!StringUtils.isEmpty(medCenter.getMedCenterEmail())){
            String message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/doctor/activate/%s",
                    medCenter.getUsername(),
                    medCenter.getActivationCode()
            );
            mailDelivery.send(medCenter.getMedCenterEmail(), "Activation code", message);
        }

        medCenterRepository.saveAndFlush(medCenter);
        return true;
    }

    @Override
    public boolean activateMedCenter(String code) {

        MedCenter medCenter = medCenterRepository.findByActivationCode(code);

        if(medCenter == null){
            return false;
        }

        medCenter.setActivationCode(null);
        medCenterRepository.saveAndFlush(medCenter);

        return true;
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        Doctor doctor1 = doctorRepository.findByUsername(doctor.getUsername());

        if(doctor1 != null){
            System.out.println("ERROR");
            return false;
        }

        doctor.setStatus(true);
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctor.setActivationCode(UUID.randomUUID().toString());

        if(!StringUtils.isEmpty(doctor.getDoctorEmail())){
            String message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/doctor/activate/%s",
                    doctor.getUsername(),
                    doctor.getActivationCode()
            );
            mailDelivery.send(doctor.getDoctorEmail(), "Activation code", message);
        }

        doctorRepository.saveAndFlush(doctor);
        return true;
    }

    @Override
    public boolean activateDoctor(String code) {

        Doctor doctor = doctorRepository.findByActivationCode(code);

        if(doctor == null){
            return false;
        }

        doctor.setActivationCode(null);
        doctorRepository.saveAndFlush(doctor);

        return true;
    }

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
}
