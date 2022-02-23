package kz.spring.authentication.repository;

import kz.spring.authentication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsCustomerByUsernameAndPassword(String username, String password);
    Customer getById (Long customerId);
    Customer getByCustomerName(String customerName);
    List<Customer> getCustomersBy();
}
