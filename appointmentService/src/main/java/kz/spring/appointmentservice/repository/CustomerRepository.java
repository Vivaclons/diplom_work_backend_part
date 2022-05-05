package kz.spring.appointmentservice.repository;

import kz.spring.appointmentservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer getCustomerByCustomerId(Long id);
    Customer findCustomerByUsername(String username);
}
