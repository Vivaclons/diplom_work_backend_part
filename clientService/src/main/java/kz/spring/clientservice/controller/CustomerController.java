package kz.spring.clientservice.controller;

import kz.spring.clientservice.model.Customer;
import kz.spring.clientservice.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("")
    public List<Customer> getAllCustomers(){
        return iCustomerService.getAllCustomerBy();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id){
        return iCustomerService.getById(id);
    }

    @GetMapping("/customerName")
    public Customer getByCustomerName(@PathVariable("customerName") String customerName){
        return iCustomerService.getByCustomerName(customerName);
    }

    @GetMapping("/email")
    public Customer getByCustomerEmail(@PathVariable("email") String email){
        return iCustomerService.getByCustomerEmail(email);
    }

    @DeleteMapping
    public void deleteCustomerByID(@PathVariable("id") Long id){
        iCustomerService.DeleteById(id);
    }

    @PostMapping("/create")
    public void createCustomer(@RequestBody Customer customer){
        iCustomerService.update(customer);
    }

    @PutMapping("/update")
    public void updateCustomer(@RequestBody Customer customer){
        iCustomerService.update(customer);
    }
}
