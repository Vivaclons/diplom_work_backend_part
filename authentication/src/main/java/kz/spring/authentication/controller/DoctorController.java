package kz.spring.authentication.controller;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private IDoctorService iDoctorService;

    @GetMapping("/private/find/{email}")
    public ResponseEntity<?> findDoctorEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(iDoctorService.getDoctorByEmail(email));
    }

    @PutMapping(value = "/private/update/{email}", consumes = {"application/xml","application/json"})
    public void updateCustomer(@RequestBody Doctor doctor, @PathVariable("email") String email){
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
        iDoctorService.update(doctor, email);
    }
}
