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

    @GetMapping("/search")
    public ResponseEntity<?> searchCustomerByName(@RequestParam("customerName") String customerName) {
        return ResponseEntity.ok(iCustomerService.searchCustomerByCustomerName(customerName));
    }

    @PatchMapping("/add-doctor")
    public ResponseEntity<?> addDoctor(@RequestParam("customer_id") Long customer_id, @RequestParam("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iCustomerService.addDoctor(customer_id, doctor_id));
    }

    @PatchMapping("/change-doctor")
    public ResponseEntity<?> updateCustomerDoctorById(@RequestParam("customer_id") Long customer_id, @RequestParam("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iCustomerService.updateDoctor(customer_id, doctor_id));
    }

    @PatchMapping("/remove-doctor")
    public ResponseEntity<?> removeDoctor(@RequestParam("customer_id") Long customer_id, @RequestParam("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iCustomerService.removeDoctor(customer_id, doctor_id));
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        return iCustomerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id){
        Customer customer = iCustomerService.getById(id);
        return ResponseEntity.ok(customer);
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
