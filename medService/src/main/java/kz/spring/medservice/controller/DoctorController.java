package kz.spring.medservice.controller;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService iDoctorService;

    @GetMapping("/public/search/{doctorName}")
    public ResponseEntity<?> searchDoctorByName(@PathVariable("doctorName") String doctorName) {
        return ResponseEntity.ok(iDoctorService.searchDoctorByDoctorName(doctorName));
    }

//    @GetMapping("/search-doctor-specialty/{specialtyName}")
//    public ResponseEntity<?> searchDoctorBySpecialty(@PathVariable("specialtyName") String specialtyName) {
//        return ResponseEntity.ok(iDoctorService.searchDoctorsBySpecialty(specialtyName));
//    }

    @PatchMapping("/private/add-specialty/{doctor_id}/{specialty_id}")
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

    @GetMapping("/public/all")
    public List<Doctor> getAllDoctors(){
        return iDoctorService.getAllDoctor();
    }

    @GetMapping("/public/all/workTime/{date}")
    public List<Doctor> getAllDoctorWorkTime(@PathVariable("date") String date){
        return iDoctorService.getAllDoctorByWorkTime(date);
    }

    @GetMapping("/public/all/nearDoctor/{lat}/{lon}")
    public List<Doctor> getNearDoctor(@PathVariable("lat") String lat, @PathVariable("lon") String lon){
        return iDoctorService.nearDoctor(lat, lon);
    }

    @GetMapping("/private/{id}")
    public Doctor getDoctorById(@PathVariable("id") Long id){
        return iDoctorService.getDoctorById(id);
    }

    @GetMapping("/public/doctorName/{doctorName}")
    public Doctor getDoctorName(@PathVariable("doctorName") String doctorName){
        return iDoctorService.getByDoctorName(doctorName);
    }

    @GetMapping("/public/specialty/{specialty}")
    public List<Doctor> getDoctorSpecialty(@PathVariable("specialty") String specialty){
        return iDoctorService.searchSpecialtyDoctor(specialty);
    }

    @DeleteMapping("/private/delete/{doctorId}")
    public void deleteDoctorByID(@PathVariable("doctorId") Long doctorId){
        iDoctorService.DeleteById(doctorId);
    }

    @PostMapping(value = "/private/create", consumes = {"application/xml","application/json"})
    public void createDoctor(@RequestBody Doctor doctor){
        iDoctorService.update(doctor);
    }

    @PutMapping(value = "/private/update", consumes = {"application/xml","application/json"})
    public void updateDoctor(@RequestBody Doctor doctor){
        iDoctorService.update(doctor);
    }
}
