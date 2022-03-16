package kz.spring.authentication.controller;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.model.MedCenter;
import kz.spring.authentication.service.impl.ICustomerService;
import kz.spring.authentication.service.impl.IDoctorService;
import kz.spring.authentication.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IDoctorService iDoctorService;

    @Autowired
    private IMedCenterService iMedCenterService;

    //add customer
    @PostMapping("/add-customer")
    public String addCustomer(@RequestBody Customer customer){

        if(!iCustomerService.addUser(customer)){
            return "ERROR with registration customer";
        }

        return "OK!";
    }

    @GetMapping("/activate/{code}")
    public String activate(Customer customer, @PathVariable String code){

        boolean isActivated = iCustomerService.activateCustomer(code);

        if(!isActivated){
            return "OK!";
        } else{
            System.out.println("ERROR code is not found");
        }

        return "code is done!";
    }


    //add doctor
    @PostMapping("/add-doctor")
    public String addDoctor(@RequestBody Doctor doctor, @RequestParam("password2") String passwordConf, Map<String, Object> map){

        if(doctor.getPassword() != null && !doctor.getPassword().equals(passwordConf)){
            map.put("password error", "Password-1 not some with password-2");
            return " Doctor password error password-1 != password-2";
        }

        if(!iDoctorService.addDoctor(doctor)){
            map.put("username error", "Doctor not found!");
            return "ERROR with registration doctor";
        }

        return "OK!";
    }

    @GetMapping("/doctor/activate/{code}")
    public String activate(Doctor doctor, @PathVariable String code){

        boolean isActivated = iDoctorService.activateDoctor(code);


        if(!isActivated){
            System.out.println("Doctor activated");
        } else{
            System.out.println("ERROR code is not found");
        }

        return "code is done!";
    }

    //add medCenter
    @PostMapping("/add-medCenter")
    public String addMedCenter(@RequestBody MedCenter medCenter, @RequestParam("password2") String passwordConf, Map<String, Object> map){

        if(medCenter.getPassword() != null && !medCenter.getPassword().equals(passwordConf)){
            map.put("password error", "Password-1 not some with password-2");
            return " Medical center password error password-1 != password-2";
        }

        if(!iMedCenterService.addMedCenter(medCenter)){
            map.put("username error", "Medical center not found!");
            return "ERROR with registration medical center";
        }

        return "OK!";
    }

    @GetMapping("/medCenter/activate/{code}")
    public String activate(MedCenter medCenter, @PathVariable String code){

        boolean isActivated = iMedCenterService.activateMedCenter(code);


        if(!isActivated){
            System.out.println("Medical center activated");
        } else{
            System.out.println("ERROR code is not found");
        }

        return "code is done!";
    }
}
