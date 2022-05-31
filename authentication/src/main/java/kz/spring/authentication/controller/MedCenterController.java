package kz.spring.authentication.controller;

import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.model.MedCenter;
import kz.spring.authentication.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medCenter")
@CrossOrigin(origins = "*")
public class MedCenterController {

    @Autowired
    private IMedCenterService iMedCenterService;

    @GetMapping("/private/find/{email}")
    public ResponseEntity<?> findMedCenterEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(iMedCenterService.getMedCenterByEmail(email));
    }

    @PutMapping(value = "/private/update/{email}", consumes = {"application/xml","application/json"})
    public void updateMedCenter(@RequestBody MedCenter medCenter, @PathVariable("email") String email){
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
        iMedCenterService.updateMedCenter(medCenter, email);
    }
}
