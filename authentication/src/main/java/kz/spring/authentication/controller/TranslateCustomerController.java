package kz.spring.authentication.controller;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.CustomerKz;
import kz.spring.authentication.model.CustomerRu;
import kz.spring.authentication.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("translate")
public class TranslateCustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/kz/customer/username/{username}")
    public CustomerKz getCustomersKzById(@PathVariable("username") String username){
        return iCustomerService.getCustomerKzById(username);
    }

    @GetMapping("/ru/customer/username/{username}")
    public CustomerRu getCustomersRuById(@PathVariable("username") String username){
        return iCustomerService.getCustomerRuById(username);
    }

    @GetMapping("/kz/customer/name/{customerName}")
    public CustomerKz getByCustomerKZName(@PathVariable("customerName") String customerName){
        return iCustomerService.getByCustomerKzName(customerName);
    }

    @GetMapping("/ru/customer/name/{customerName}")
    public CustomerRu getByCustomerRuName(@PathVariable("customerName") String customerName){
        return iCustomerService.getByCustomerRuName(customerName);
    }

    @PutMapping(value = "/kz/customer/update", consumes = {"application/xml","application/json"})
    public void updateCustomer(@RequestBody CustomerKz customerKz){
        iCustomerService.updateCusKz(customerKz);
    }

    @PutMapping(value = "/ru/customer/update", consumes = {"application/xml","application/json"})
    public void updateCustomer(@RequestBody CustomerRu customerRu){
        iCustomerService.updateCusRu(customerRu);
    }
}
