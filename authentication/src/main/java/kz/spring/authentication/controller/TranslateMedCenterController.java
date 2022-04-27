package kz.spring.authentication.controller;

import kz.spring.authentication.model.DoctorKz;
import kz.spring.authentication.model.DoctorRu;
import kz.spring.authentication.model.MedCenterKz;
import kz.spring.authentication.model.MedCenterRu;
import kz.spring.authentication.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("translate")
public class TranslateMedCenterController {

    @Autowired
    private IMedCenterService iMedCenterService;

    @GetMapping("/kz/medCenter/username/{username}")
    public MedCenterKz getMedCenterKzById(@PathVariable("username") String username){
        return iMedCenterService.getMedCenterKzByUsername(username);
    }

    @GetMapping("/ru/medCenter/username/{username}")
    public MedCenterRu getMedCenterRuById(@PathVariable("username") String username){
        return iMedCenterService.getMedCenterRuByUsername(username);
    }

    @GetMapping("/kz/medCenter/name/{customerName}")
    public MedCenterKz getByMedCenterKzName(@PathVariable("customerName") String customerName){
        return iMedCenterService.getByMedCenterKzName(customerName);
    }

    @GetMapping("/ru/medCenter/name/{customerName}")
    public MedCenterRu getByMedCenterRuName(@PathVariable("customerName") String customerName){
        return iMedCenterService.getByMedCenterRuName(customerName);
    }

    @PutMapping(value = "/kz/medCenter/update", consumes = {"application/xml","application/json"})
    public void updateMCKz(@RequestBody MedCenterKz medCenterKz){
        iMedCenterService.updateMCKz(medCenterKz);
    }

    @PutMapping(value = "/ru/medCenter/update", consumes = {"application/xml","application/json"})
    public void updateMCRu(@RequestBody MedCenterRu medCenterRu){
        iMedCenterService.updateMCRu(medCenterRu);
    }
}
