package kz.spring.authentication.controller;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.model.MedCenter;
import kz.spring.authentication.service.impl.ICustomerService;
import kz.spring.authentication.service.impl.IDoctorService;
import kz.spring.authentication.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IDoctorService iDoctorService;

    @Autowired
    private IMedCenterService iMedCenterService;


    //Admin customer controller
    @GetMapping("/customer/{customerId}")
    public Customer getCustomersById(@PathVariable("customerId") Long customerId){
        return iCustomerService.getCustomerById(customerId);
    }

    @GetMapping("/customer/{customerName}")
    public Customer getByCustomerName(@PathVariable("customerName") String customerName){
        return iCustomerService.getByCustomerName(customerName);
    }

    @DeleteMapping("/delete/customer/{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") Long customerId){
        iCustomerService.DeleteByID(customerId);
    }

    @GetMapping("/customer/all")
    public List<Customer> getAllCustomer(){
        return iCustomerService.getAllCustomersBy();
    }

    @PutMapping("/customer/update")
    public void updateCustomer(@RequestBody Customer customer){
        iCustomerService.updateCus(customer);
    }

    //Admin doctor controller
    @GetMapping("/doctor/{doctorId}")
    public Doctor getDoctorsById(@PathVariable("doctorId") Long doctorId){
        return iDoctorService.getDoctorById(doctorId);
    }

    @GetMapping("/doctor/{doctorName}")
    public Doctor getByDoctorName(@PathVariable("doctorName") String doctorName){
        return iDoctorService.getByDoctorName(doctorName);
    }

    @DeleteMapping("/delete/doctor/{doctorId}")
    public void deleteDoctorById(@PathVariable("doctorId") Long doctorId){
        iDoctorService.DeleteByIDDoc(doctorId);
    }

    @GetMapping("/doctor/all")
    public List<Doctor> getAllDoctor(){
        return iDoctorService.getAllDoctorsBy();
    }

    @PutMapping("/doctor/update")
    public void updateDoctor(@RequestBody Doctor doctor){
        iDoctorService.updateDoc(doctor);
    }

    //Admin medical center controller
    @GetMapping("/medCenter/{medCenterId}")
    public MedCenter getMedCentersById(@PathVariable("medCenterId") Long medCenterId){
        return iMedCenterService.getMedCenterById(medCenterId);
    }

    @GetMapping("/medCenter/{medCenterName}")
    public MedCenter getByMedCenterName(@PathVariable("medCenterName") String medCenterName){
        return iMedCenterService.getByMedCenterName(medCenterName);
    }

    @DeleteMapping("/delete/medCenter/{medCenterId}")
    public void deleteMedCenterById(@PathVariable("medCenterId") Long medCenterId){
        iMedCenterService.DeleteByIDMed(medCenterId);
    }

    @GetMapping("/medCenter/all")
    public List<MedCenter> getAllMedCenter(){
        return iMedCenterService.getAllMedCentersBy();
    }

    @PutMapping("/medCenter/update")
    public void updateMedCenter(@RequestBody MedCenter medCenter){
        iMedCenterService.updateMed(medCenter);
    }

}
