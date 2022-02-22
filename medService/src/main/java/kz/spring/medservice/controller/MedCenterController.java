package kz.spring.medservice.controller;

import kz.spring.medservice.model.MedCenter;
import kz.spring.medservice.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medCenter")
public class MedCenterController {

    @Autowired
    private IMedCenterService iMedCenterService;

    @GetMapping("/all")
    public List<MedCenter> getAllMedCenter(){
        return iMedCenterService.getAllMedCenter();
    }

    @GetMapping("/search/medCenter-name")
    public ResponseEntity<?> searchMedCenterByName(@RequestParam("medCenterName") String medCenterName) {
        return ResponseEntity.ok(iMedCenterService.searchMedCenterByMedCenterName(medCenterName));
    }

    @GetMapping("/search/medCenter-address")
    public ResponseEntity<?> searchMedCenterByAddress(@RequestParam("medCenterAddress") String medCenterAddress) {
        return ResponseEntity.ok(iMedCenterService.searchMedCenterByMedCenterAddress(medCenterAddress));
    }

    @PatchMapping("/add-doctor")
    public ResponseEntity<?> addDoctor(@RequestParam("medCenter_id") Long medCenter_id, @RequestParam("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iMedCenterService.addDoctor(medCenter_id, doctor_id));
    }

    @PatchMapping("/change-doctor")
    public ResponseEntity<?> updateMedCenterDoctorById(@RequestParam("medCenter_id") Long medCenter_id, @RequestParam("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iMedCenterService.updateDoctor(medCenter_id, doctor_id));
    }

    @PatchMapping("/remove-doctor")
    public ResponseEntity<?> removeDoctor(@RequestParam("medCenter_id") Long medCenter_id, @RequestParam("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iMedCenterService.removeDoctor(medCenter_id, doctor_id));
    }

    @GetMapping("/{id}")
    public MedCenter getMedCenterById(@PathVariable("id") Long id){
        return iMedCenterService.getById(id);
    }

    @GetMapping("/medCenterName")
    public MedCenter getByMedCenterName(@PathVariable("medCenterName") String medCenterName){
        return iMedCenterService.getByMedCenterName(medCenterName);
    }

    @GetMapping("/address")
    public MedCenter getByMedCenterAddress(@PathVariable("address") String address){
        return iMedCenterService.getByMedCenterAddress(address);
    }

    @DeleteMapping
    public void deleteMedCenterByID(@PathVariable("id") Long id){
        iMedCenterService.DeleteById(id);
    }

    @PostMapping("/create")
    public void createDoctor(@RequestBody MedCenter medCenter){
        iMedCenterService.update(medCenter);
    }

    @PutMapping("/update")
    public void updateMedCenter(@RequestBody MedCenter medCenter){
        iMedCenterService.update(medCenter);
    }
}
