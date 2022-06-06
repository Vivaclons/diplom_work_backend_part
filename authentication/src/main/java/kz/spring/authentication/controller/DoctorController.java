package kz.spring.authentication.controller;

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
    public void updateDoctor(@RequestBody Doctor doctor, @PathVariable("email") String email){
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

    @PatchMapping("/private/add-specialty/{email}/{specialty_id}")
    public ResponseEntity<?> addSpecialty(@PathVariable("email") String email, @PathVariable("specialty_id") Long specialty_id) {
        return ResponseEntity.ok(iDoctorService.addSpecialty(email, specialty_id));
    }

    @PatchMapping("/private/change-specialty/{email}/{specialty_id}")
    public ResponseEntity<?> updateDoctorSpecialtyById(@PathVariable("email") String email, @PathVariable("specialty_id") Long specialty_id) {
        return ResponseEntity.ok(iDoctorService.updateSpecialty(email, specialty_id));
    }

    @PatchMapping("/private/remove-specialty/{email}/{specialty_id}")
    public ResponseEntity<?> removeSpecialty(@PathVariable("email") String email, @PathVariable("specialty_id") Long specialty_id) {
        return ResponseEntity.ok(iDoctorService.removeSpecialty(email, specialty_id));
    }
}
