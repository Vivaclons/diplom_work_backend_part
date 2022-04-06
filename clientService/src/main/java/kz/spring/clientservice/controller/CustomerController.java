package kz.spring.clientservice.controller;

import kz.spring.clientservice.model.Customer;
import kz.spring.clientservice.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/public/search/{customerName}")
    public ResponseEntity<?> searchCustomerByName(@PathVariable("customerName") String customerName) {
        return ResponseEntity.ok(iCustomerService.searchCustomerByCustomerName(customerName));
    }

    @PatchMapping("/private/add-doctor/{customer_id}/{doctor_id}")
    public ResponseEntity<?> addDoctor(@PathVariable("customer_id") Long customer_id, @PathVariable("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iCustomerService.addDoctor(customer_id, doctor_id));
    }

    @PatchMapping("/private/change-doctor/{customer_id}/{doctor_id}")
    public ResponseEntity<?> updateCustomerDoctorById(@PathVariable("customer_id") Long customer_id, @PathVariable("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iCustomerService.updateDoctor(customer_id, doctor_id));
    }

    @PatchMapping("/private/remove-doctor/{customer_id}/{doctor_id}")
    public ResponseEntity<?> removeDoctor(@PathVariable("customer_id") Long customer_id, @PathVariable("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iCustomerService.removeDoctor(customer_id, doctor_id));
    }

    @GetMapping("/private/all")
    public List<Customer> getAllCustomers(){
        return iCustomerService.getAllCustomer();
    }

    @GetMapping("/private/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id){
        Customer customer = iCustomerService.getById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/public/customerName/{customerName}")
    public Customer getByCustomerName(@PathVariable("customerName") String customerName){
        return iCustomerService.getByCustomerName(customerName);
    }

    @GetMapping("/public/email/{email}")
    public Customer getByCustomerEmail(@PathVariable("email") String email){
        return iCustomerService.getByCustomerEmail(email);
    }

    @DeleteMapping("/private/delete/{id}")
    public void deleteCustomerByID(@PathVariable("id") Long id){
        iCustomerService.DeleteById(id);
    }

    @PostMapping(value = "/private/create", consumes = {"application/xml","application/json"})
    public void createCustomer(@RequestBody Customer customer){
        iCustomerService.update(customer);
    }

    @PutMapping(value = "/private/update", consumes = {"application/xml","application/json"})
    public void updateCustomer(@RequestBody Customer customer){
        iCustomerService.update(customer);
    }

    @PutMapping(value = "/private/rating/{customerId}/{rating}", consumes = {"application/xml","application/json"})
    public void rating(@PathVariable("customerId") Long customerId, @PathVariable("rating") double rating){
        iCustomerService.rating(customerId, rating);
    }
}
