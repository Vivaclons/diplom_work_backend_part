package kz.spring.authentication.controller;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customerAuth")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("")
    public List<Customer> getAllCustomer(){
        return iCustomerService.getAllCustomersBy();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomersById(@PathVariable("customerId") Long customerId){
        return iCustomerService.getById(customerId);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") Long customerId){
        iCustomerService.DeleteByID(customerId);
    }

    @PostMapping("/create")
    public void createCustomer(@RequestBody Customer customer){
        iCustomerService.update(customer);
    }

    @PostMapping("/create/admin")
    public void createAdmin(@RequestBody Customer customer){
        iCustomerService.update(customer);
    }

    @PutMapping("/update")
    public void updateCustomer(@RequestBody Customer customer){
        iCustomerService.update(customer);
    }

    @PostMapping("/auth/customer")
    public void authUser(){
    }


    @PostMapping("/auth/admin")
    public void authAdmin(){
    }
}
