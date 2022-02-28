package kz.spring.authentication.controller;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Role;
import kz.spring.authentication.service.impl.ICustomerService;
import kz.spring.authentication.service.impl.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customerAuth")
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IRoleService iRoleService;

    @GetMapping("")
    public List<Customer> getAllCustomer(){
        return iCustomerService.getAllCustomersBy();
    }

//    @GetMapping("/all/roles")
//    public List<Role> getRoles(){
//        return iRoleService.();
//    }

    @PostMapping("/add-role")
    public ResponseEntity<Role> addRoleToCustomer(@RequestBody Customer customer, @RequestBody String role){
        this.iRoleService.addRoleToCustomer(customer, role);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomersById(@PathVariable("customerId") Long customerId){
        return iCustomerService.getById(customerId);
    }

    @DeleteMapping("/delete/{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") Long customerId){
        iCustomerService.DeleteByID(customerId);
    }

    @PostMapping("/create/user")
    public void createCustomer(@RequestBody Customer customer, @RequestBody String role){
        addRoleToCustomer(customer, role);
        iCustomerService.update(customer);
    }

    @PostMapping("/create/admin")
    public void createAdmin(@RequestBody Customer customer){
        iCustomerService.update(customer);
    }

    @PutMapping("/update")
    public void updateCustomer(@RequestBody Customer customer, @RequestBody String role){
        addRoleToCustomer(customer, role);
        iCustomerService.update(customer);
    }

    @PostMapping("/auth/customer")
    public void authUser(){
    }

    @PostMapping("/auth/admin")
    public void authAdmin(){
    }
}
