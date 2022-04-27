package kz.spring.authentication.controller;

import kz.spring.authentication.model.DoctorKz;
import kz.spring.authentication.model.DoctorRu;
import kz.spring.authentication.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("translate")
public class TranslateDoctorController {
    @Autowired
    private IDoctorService iDoctorService;

    @GetMapping("/kz/doctor/username/{username}")
    public DoctorKz getDoctorKzById(@PathVariable("username") String username){
        return iDoctorService.getDoctorKzByUsername(username);
    }

    @GetMapping("/ru/doctor/username/{username}")
    public DoctorRu getDoctorRuById(@PathVariable("username") String username){
        return iDoctorService.getDoctorRuByUsername(username);
    }

    @GetMapping("/kz/doctor/name/{customerName}")
    public DoctorKz getByDoctorKZName(@PathVariable("customerName") String customerName){
        return iDoctorService.getByDoctorKzName(customerName);
    }

    @GetMapping("/ru/doctor/name/{customerName}")
    public DoctorRu getByDoctorRuName(@PathVariable("customerName") String customerName){
        return iDoctorService.getByDoctorRuName(customerName);
    }

    @PutMapping(value = "/kz/doctor/update", consumes = {"application/xml","application/json"})
    public void updateDoctor(@RequestBody DoctorKz doctorKz){
        iDoctorService.updateDocKz(doctorKz);
    }

    @PutMapping(value = "/ru/doctor/update", consumes = {"application/xml","application/json"})
    public void updateDoctor(@RequestBody DoctorRu doctorRu){
        iDoctorService.updateDocRu(doctorRu);
    }
}

