package kz.spring.medservice.controller;

import kz.spring.medservice.model.MedCenter;
import kz.spring.medservice.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medCenter")
@CrossOrigin(origins = "*")
public class MedCenterController {

    @Autowired
    private IMedCenterService iMedCenterService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/public/all")
    public List<MedCenter> getAllMedCenter(){
        return iMedCenterService.getAllMedCenter();
    }

    @GetMapping("/public/search/medCenter-name/{medCenterName}")
    public ResponseEntity<?> searchMedCenterByName(@PathVariable("medCenterName") String medCenterName) {
        return ResponseEntity.ok(iMedCenterService.searchMedCenterByMedCenterName(medCenterName));
    }

    @GetMapping("/public/search/medCenter-address/{medCenterAddress}")
    public ResponseEntity<?> searchMedCenterByAddress(@PathVariable("medCenterAddress") String medCenterAddress) {
        return ResponseEntity.ok(iMedCenterService.searchMedCenterByMedCenterAddress(medCenterAddress));
    }

    @PatchMapping("/private/add-doctor/{medCenter_id}/{doctor_id}")
    public ResponseEntity<?> addDoctor(@PathVariable("medCenter_id") Long medCenter_id, @PathVariable("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iMedCenterService.addDoctor(medCenter_id, doctor_id));
    }

    @PatchMapping("/private/change-doctor/{medCenter_id}/{doctor_id}")
    public ResponseEntity<?> updateMedCenterDoctorById(@PathVariable("medCenter_id") Long medCenter_id, @PathVariable("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iMedCenterService.updateDoctor(medCenter_id, doctor_id));
    }

    @PatchMapping("/private/remove-doctor/{medCenter_id}/{doctor_id}")
    public ResponseEntity<?> removeDoctor(@PathVariable("medCenter_id") Long medCenter_id, @PathVariable("doctor_id") Long doctor_id) {
        return ResponseEntity.ok(iMedCenterService.removeDoctor(medCenter_id, doctor_id));
    }

    @GetMapping("/public/{id}")
    public MedCenter getMedCenterById(@PathVariable("id") Long id){
        return iMedCenterService.getById(id);
    }

    @GetMapping("/public/medCenterName/{medCenterName}")
    public MedCenter getByMedCenterName(@PathVariable("medCenterName") String medCenterName){
        return iMedCenterService.getByMedCenterName(medCenterName);
    }

    @GetMapping("/public/address/{address}")
    public MedCenter getByMedCenterAddress(@PathVariable("address") String address){
        return iMedCenterService.getByMedCenterAddress(address);
    }

    @GetMapping("/public/all/nearMedCenter/{lat}/{lon}")
    public List<MedCenter> getNearMedCenter(@PathVariable("lat") String lat, @PathVariable("lon") String lon){
        return iMedCenterService.getNearMedCenter(lat, lon);
    }

    @DeleteMapping("/private/delete/{medCenterId}")
    public void deleteMedCenterByID(@PathVariable("medCenterId") Long medCenterId){
        iMedCenterService.DeleteById(medCenterId);
    }

    @PostMapping(value = "/private/create", consumes = {"application/xml","application/json"})
    public void createDoctor(@RequestBody MedCenter medCenter){
        iMedCenterService.update(medCenter);
    }

    @PutMapping(value = "/private/update", consumes = {"application/xml","application/json"})
    public void updateMedCenter(@RequestBody MedCenter medCenter, @RequestParam("file") MultipartFile file) throws IOException {
        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + fileName));

            medCenter.setAvatar(fileName);
        }
        iMedCenterService.update(medCenter);
    }

    @GetMapping("/public/all/workTime/{date}")
    public List<MedCenter> getAllMedCentersWorkTime(@PathVariable("date") String date){
        return iMedCenterService.getAllMedCentersWorkTime(date);
    }

    @PutMapping(value = "/private/rating/{medCenterId}/{rating}", consumes = {"application/xml","application/json"})
    public void rating(@PathVariable("medCenterId") Long medCenterId, @PathVariable("rating") double rating){
        iMedCenterService.rating(medCenterId, rating);
    }

    @GetMapping("/public/filter/{lat}/{lon}/{distanceFrom}/{distanceTo}/{ratingFrom}/{ratingTo}/{priceFrom}/{priceTo}/{time}")
    public List<MedCenter> getAllFilter(@PathVariable("lat") String lat, @PathVariable("lon") String lon,
                                        @PathVariable("distanceFrom") int distancefrom,
                                     @PathVariable("distanceTo") int distanceTo,
                                     @PathVariable("ratingFrom") int ratingFrom,
                                     @PathVariable("ratingTo") int ratingTo,
                                     @PathVariable("priceFrom") int priceFrom,
                                     @PathVariable("priceTo") int priceTo,
                                     @PathVariable("time") String time){
        return iMedCenterService.getAllFilter(lat, lon, distancefrom, distanceTo, ratingFrom, ratingTo, priceFrom, priceTo, time);
    }
}
