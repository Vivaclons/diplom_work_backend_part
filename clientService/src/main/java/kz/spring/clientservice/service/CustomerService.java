package kz.spring.clientservice.service;

import kz.spring.clientservice.model.Customer;
import kz.spring.clientservice.model.Doctor;
import kz.spring.clientservice.repository.CustomerRepository;
import kz.spring.clientservice.repository.DoctorRepository;
import kz.spring.clientservice.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Customer> searchCustomerByCustomerName(String customerName) {
        return customerRepository.getCustomerByCustomerNameIsLike("%" + customerName + "%");
    }

    @Override
    public Customer removeDoctor(Long customerId, Long doctorId) {
        Customer customer = customerRepository.getByCustomerId(customerId);

        boolean check = false;

        for(int i = 0; i < customer.getDoctors().size(); i++){
            if(customer.getDoctors().get(i).getDoctorId().equals(doctorId)){
                customer.getDoctors().remove(i);
                check = true;
                break;
            }
        }

        if(check){
            return customerRepository.saveAndFlush(customer);
        }
        return null;
    }

    @Override
    public Customer addDoctor(Long customerId, Long doctorId) {

        Customer customer = customerRepository.getByCustomerId(customerId);

        Doctor doctor = restTemplate.getForObject("http://localhost:8082/med-service/doctor/" + doctorId, Doctor.class);

        boolean check = false;

        if(doctor != null && customer != null){
            customer.getDoctors().add(doctor);
            if(!check){
                return customerRepository.saveAndFlush(customer);
            }
        }
        return null;
    }

    @Override
    public Customer updateDoctor(Long customerId, Long doctorId) {

        Customer customer = customerRepository.getByCustomerId(doctorId);

        if (customer != null && customer.getCustomerId() != null && customer.getCustomerId() != 0L) {
            for (int i = 0; i < customer.getDoctors().size(); i++) {
                customer.getDoctors().get(i).setDoctorId(doctorId);
                return customerRepository.saveAndFlush(customer);
            }
        }
        return null;
    }

    @Override
    public Customer getById(Long id) {
        Customer customer = customerRepository.getCustomerByCustomerId(id);
        return customer;
    }

    @Override
    public Customer getByCustomerName(String customerName) {
        return customerRepository.getByCustomerName(customerName);
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
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void DeleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
