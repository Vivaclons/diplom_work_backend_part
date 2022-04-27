package kz.spring.authentication.repository;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.CustomerKz;
import kz.spring.authentication.model.CustomerRu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerKzRepository extends JpaRepository<CustomerKz, Long> {
    CustomerKz findByUsername(String username);
    CustomerKz findByEmail(String email);
    CustomerKz findByActivationCode(String code);
    CustomerKz getByCustomerName(String customerName);

}
