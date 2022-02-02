package kz.spring.medservice.controller;

import kz.spring.medservice.model.MedCenter;
import kz.spring.medservice.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medCenter")
public class MedCenterController {

    @Autowired
    private IMedCenterService iMedCenterService;

    @GetMapping("")
    public List<MedCenter> getAllMedCenter(){
        return iMedCenterService.getAllMedCenterBy();
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
