package kz.spring.authentication.controller;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @PutMapping(value = "/private/update/{email}", consumes = {"application/xml","application/json"})
    public void updateCustomer(@RequestBody Customer customer, @PathVariable("email") String email){
//            , @RequestParam("file")MultipartFile file) throws IOException {
//        if(file != null && !file.getOriginalFilename().isEmpty()){
//            File uploadDir = new File(uploadPath);
//
//            if(!uploadDir.exists()){
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String fileName = uuidFile + "." + file.getOriginalFilename();
//
//            file.transferTo(new File(uploadPath + "/" + fileName));
//
//            customer.setAvatar(fileName);
//        }
        iCustomerService.update(customer, email);
    }

    @GetMapping("/private/find/{email}")
    public ResponseEntity<?> findUsername(@PathVariable("email") String email){
        return ResponseEntity.ok(iCustomerService.getCustomerByEmail(email));
    }

}
