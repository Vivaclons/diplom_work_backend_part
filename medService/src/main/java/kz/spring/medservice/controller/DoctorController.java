package kz.spring.medservice.controller;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService iDoctorService;

    @GetMapping("")
    public List<Doctor> getAllDoctors(){
        return iDoctorService.getAllDoctor();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable("id") Long id){
        return iDoctorService.getById(id);
    }

    @GetMapping("/doctorName")
    public Doctor getDoctorName(@PathVariable("doctorName") String doctorName){
        return iDoctorService.getByDoctorName(doctorName);
    }

    @DeleteMapping
    public void deleteDoctorByID(@PathVariable("id") Long id){
        iDoctorService.DeleteById(id);
    }

    @PostMapping("/create")
    public void createDoctor(@RequestBody Doctor doctor){
        iDoctorService.update(doctor);
    }

    @PutMapping("/update")
    public void updateDoctor(@RequestBody Doctor doctor){
        iDoctorService.update(doctor);
    }
}
