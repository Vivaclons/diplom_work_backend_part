package kz.spring.authentication.repository;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.CustomerKz;
import kz.spring.authentication.model.CustomerRu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRuRepository extends JpaRepository<CustomerRu, Long> {
    CustomerRu findByUsername(String username);
    CustomerRu findByEmail(String email);
    CustomerRu findByActivationCode(String code);
    CustomerRu getByCustomerName(String customerName);
}
