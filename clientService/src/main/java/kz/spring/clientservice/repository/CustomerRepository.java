package kz.spring.clientservice.repository;

import kz.spring.clientservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer getByCustomerId(Long id);
    Customer getByCustomerEmail(String email);
    Customer getCustomerByCustomerId(Long id);
    Customer getByCustomerName(String customerName);
}
