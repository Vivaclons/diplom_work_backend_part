package kz.spring.authentication.repository;

import kz.spring.authentication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsCustomerByUsernameAndPassword(String username, String password);
    Customer getById (Long customerId);
    Customer getByCustomerName(String customerName);
    List<Customer> getCustomersBy();
    Customer findByUsername(String username);
    Customer findByCustomerTelNumber(String telNumber);
    Customer findByActivationCode(String code);
    Customer findCustomerByEmail(String email);
}
