package kz.spring.analysisservice.repository;

import kz.spring.analysisservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer getCustomerByCustomerId(Long id);
    Customer findCustomerByEmail(String email);
}
