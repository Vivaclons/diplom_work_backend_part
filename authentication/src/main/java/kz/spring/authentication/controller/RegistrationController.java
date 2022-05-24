package kz.spring.authentication.controller;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.model.MedCenter;
import kz.spring.authentication.service.impl.ICustomerService;
import kz.spring.authentication.service.impl.IDoctorService;
import kz.spring.authentication.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("registration")
@CrossOrigin(origins = "http://localhost:4200/")
public class RegistrationController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IDoctorService iDoctorService;

    @Autowired
    private IMedCenterService iMedCenterService;

    //add customer
    @PostMapping(value = "/add-customer", consumes = {"application/xml","application/json"})
    public String addCustomer(@RequestBody Customer customer){

        if(!iCustomerService.addUser(customer)){
            return "ERROR with registration customer";
        }

        if(customer.getRoles().equals("DOCTOR")){
            Doctor doctor = new Doctor();
            doctor.setDoctorName(customer.getCustomerName());
            doctor.setDoctorSurname(customer.getCustomerSurname());
            doctor.setDoctorEmail(customer.getEmail());
            doctor.setPassword(customer.getPassword());
            addDoctor(doctor);
        } else if(customer.getRoles().equals("MED_CENTER")){
            MedCenter medCenter = new MedCenter();
            medCenter.setMedCenterName(customer.getCustomerName());
            medCenter.setMedCenterEmail(customer.getEmail());
            medCenter.setPassword(customer.getPassword());
            addMedCenter(medCenter);
        }

        return "OK!";
    }

    @GetMapping("/activate/{code}")
    public String activateCustomer(@PathVariable String code){

        boolean isActivated = iCustomerService.activateCustomer(code);

        if(!isActivated){
            return "OK!";
        } else{
            System.out.println("ERROR code is not found");
        }

        return "code is done!";
    }

    @GetMapping("/forgotPass/customer/{email}")
    public String forgetPassword(@PathVariable String email){
        return iCustomerService.forgetPassword(email);
    }

    @PutMapping(value = "/change/pass/customer/{email}/{password}", consumes = {"application/xml","application/json"})
    public void updatePass(@PathVariable String email, @PathVariable String password){
        iCustomerService.updatePassword(email, password);
    }

    //add doctor
    @PostMapping(value = "/add-doctor", consumes = {"application/xml","application/json"})
    public String addDoctor(@RequestBody Doctor doctor){

        if(!iDoctorService.addDoctor(doctor)){
            return "ERROR with registration doctor";
        }

        return "OK!";
    }

    @GetMapping("/doctor/activate/{code}")
    public String activateDoctor(@PathVariable String code){

        boolean isActivated = iDoctorService.activateDoctor(code);


        if(!isActivated){
            System.out.println("Doctor activated");
        } else{
            System.out.println("ERROR code is not found");
        }

        return "code is done!";
    }

    @GetMapping("/forgotPass/doctor/{email}")
    public String forgetPasswordDoctor(@PathVariable String email){
        return iDoctorService.forgetPasswordDoctor(email);
    }

    @PutMapping(value = "/change/pass/doctor/{email}/{password}", consumes = {"application/xml","application/json"})
    public void updatePassDoctor(@PathVariable String email, @PathVariable String password){
        iDoctorService.updatePasswordDoctor(email, password);
    }

    //add medCenter
    @PostMapping(value = "/add-medCenter", consumes = {"application/xml","application/json"})
    public String addMedCenter(@RequestBody MedCenter medCenter){

        if(!iMedCenterService.addMedCenter(medCenter)){
            return "ERROR with registration medical center";
        }

        return "OK!";
    }

    @GetMapping("/medCenter/activate/{code}")
    public String activateMedCenter(@PathVariable String code){

        boolean isActivated = iMedCenterService.activateMedCenter(code);


        if(!isActivated){
            System.out.println("Medical center activated");
        } else{
            System.out.println("ERROR code is not found");
        }

        return "code is done!";
    }

    @GetMapping("/forgotPass/medCenter/{email}")
    public String forgetPasswordMedCenter(@PathVariable String email){
        return iMedCenterService.forgetPasswordMedCenter(email);
    }

    @PutMapping(value = "/change/pass/medCenter/{email}/{password}", consumes = {"application/xml","application/json"})
    public void updatePassMedCenter(@PathVariable String email, @PathVariable String password){
        iMedCenterService.updatePasswordMedCenter(email, password);
    }
}
