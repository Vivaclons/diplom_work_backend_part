package kz.spring.authentication.controller;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Role;
import kz.spring.authentication.repository.CustomerRepository;
import kz.spring.authentication.service.impl.ICustomerService;
import kz.spring.authentication.service.impl.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("registration")
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RegistrationController {

    @Autowired
    private ICustomerService iCustomerService;

    @PostMapping("/add-customer")
    public String addCustomer(@RequestBody Customer customer, Map<String, Object> map){

        if(!iCustomerService.addUser(customer)){
            map.put("message", "User not found!");
            return "ERROR with registration";
        }

        return "OK!";
    }

    @GetMapping("/activate/{code}")
    public String activate(Customer customer, @PathVariable String code){

        boolean isActivated = iCustomerService.activateCustomer(code);

        if(!isActivated){
            System.out.println("Customer activated");
        } else{
            System.out.println("ERROR code is not found");
        }

        return "code is done!";
    }

}
