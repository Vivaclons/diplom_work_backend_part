package kz.spring.medservice.controller;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService iDoctorService;

    @GetMapping("/search/{doctorName}")
    public ResponseEntity<?> searchDoctorByName(@PathVariable("doctorName") String doctorName) {
        return ResponseEntity.ok(iDoctorService.searchDoctorByDoctorName(doctorName));
    }

    @PatchMapping("/add-specialty/{doctor_id}/{specialty_id}")
    public ResponseEntity<?> addSpecialty(@PathVariable("doctor_id") Long doctor_id, @PathVariable("specialty_id") Long specialty_id) {
        return ResponseEntity.ok(iDoctorService.addSpecialty(doctor_id, specialty_id));
    }

    @PatchMapping("/change-specialty/{doctor_id}/{specialty_id}")
    public ResponseEntity<?> updateDoctorSpecialtyById(@PathVariable("doctor_id") Long doctor_id, @PathVariable("specialty_id") Long specialty_id) {
        return ResponseEntity.ok(iDoctorService.updateSpecialty(doctor_id, specialty_id));
    }

    @PatchMapping("/remove-specialty/{doctor_id}/{specialty_id}")
    public ResponseEntity<?> removeSpecialty(@PathVariable("doctor_id") Long doctor_id, @PathVariable("specialty_id") Long specialty_id) {
        return ResponseEntity.ok(iDoctorService.removeSpecialty(doctor_id, specialty_id));
    }

    @GetMapping("/all")
    public List<Doctor> getAllDoctors(){
        return iDoctorService.getAllDoctor();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable("id") Long id){
        return iDoctorService.getDoctorById(id);
    }

    @GetMapping("/doctorName/{doctorName}")
    public Doctor getDoctorName(@PathVariable("doctorName") String doctorName){
        return iDoctorService.getByDoctorName(doctorName);
    }

    @GetMapping("/specialty/{specialty}")
    public Doctor getDoctorSpecialty(@PathVariable("specialty") String specialty){
        return iDoctorService.getDoctorBySpecialty(specialty);
    }

    @DeleteMapping("/delete/{doctorId}")
    public void deleteDoctorByID(@PathVariable("doctorId") Long doctorId){
        iDoctorService.DeleteById(doctorId);
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
