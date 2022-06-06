package kz.spring.authentication.controller;

import kz.spring.authentication.model.Specialty;
import kz.spring.authentication.service.impl.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialty")
@CrossOrigin(origins = "*")
public class SpecialtyController {

    @Autowired
    private ISpecialtyService iSpecialtyService;

    @GetMapping("/all")
    public List<Specialty> getAllSpecialty(){
        return iSpecialtyService.getAllSpecialty();
    }

    @GetMapping("/{id}")
    public Specialty getSpecialtyById(@PathVariable("id") Long id){
        return iSpecialtyService.getById(id);
    }

    @DeleteMapping("/delete/{specialtyId}")
    public void deleteSpecialtyById(@PathVariable("specialtyId") Long specialtyId){
        iSpecialtyService.DeleteById(specialtyId);
    }

    @PostMapping(value = "/create", consumes = {"application/xml","application/json"})
    public void createSpecialty(@RequestBody Specialty specialty){
        iSpecialtyService.update(specialty);
    }

    @PutMapping(value = "/update", consumes = {"application/xml","application/json"})
    public void updateSpecialty(@RequestBody Specialty specialty){
        iSpecialtyService.update(specialty);
    }
}
