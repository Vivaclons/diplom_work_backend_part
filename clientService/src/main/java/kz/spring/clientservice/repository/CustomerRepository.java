package kz.spring.clientservice.repository;

import kz.spring.clientservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByCustomerId(Long id);
    List<Customer> findDoctorByCustomerName(String customerName);
    List<Customer> getCustomerByCustomerNameIsLike(String customerName);
    Customer getByCustomerId(Long id);
    Customer getByCustomerEmail(String email);
    Customer getCustomerByCustomerId(Long id);
    Customer getByCustomerName(String customerName);
}
