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

    @GetMapping("/forgotPass/customer/{email}")
    public String forgetPassword(@PathVariable String email){
        return iCustomerService.forgetPassword(email);
    }

    @PutMapping("/change/pass/customer/{email}/{password}")
    public void updatePass(@PathVariable String email, @PathVariable String password){
        iCustomerService.updatePassword(email, password);
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

    @PutMapping("/")

    //add doctor
    @PostMapping("/add-doctor")
    public String addDoctor(@RequestBody Doctor doctor){

        if(!iDoctorService.addDoctor(doctor)){
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

    @GetMapping("/forgotPass/doctor/{email}")
    public String forgetPasswordDoctor(@PathVariable String email){
        return iDoctorService.forgetPasswordDoctor(email);
    }

    @PutMapping("/change/pass/doctor/{email}/{password}")
    public void updatePassDoctor(@PathVariable String email, @PathVariable String password){
        iDoctorService.updatePasswordDoctor(email, password);
    }

    //add medCenter
    @PostMapping("/add-medCenter")
    public String addMedCenter(@RequestBody MedCenter medCenter){

        if(!iMedCenterService.addMedCenter(medCenter)){
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

    @GetMapping("/forgotPass/medCenter/{email}")
    public String forgetPasswordMedCenter(@PathVariable String email){
        return iMedCenterService.forgetPasswordMedCenter(email);
    }

    @PutMapping("/change/pass/medCenter/{email}/{password}")
    public void updatePassMedCenter(@PathVariable String email, @PathVariable String password){
        iMedCenterService.updatePasswordMedCenter(email, password);
    }
}
