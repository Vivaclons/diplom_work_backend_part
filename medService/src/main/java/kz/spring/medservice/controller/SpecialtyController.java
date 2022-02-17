package kz.spring.medservice.controller;

import kz.spring.medservice.model.Specialty;
import kz.spring.medservice.service.impl.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {

    @Autowired
    private ISpecialtyService iSpecialtyService;

    @GetMapping("")
    public List<Specialty> getAllSpecialty(){
        return iSpecialtyService.getAllSpecialty();
    }

    @GetMapping("/{id}")
    public Specialty getSpecialtyById(@PathVariable("id") Long id){
        return iSpecialtyService.getById(id);
    }

    @DeleteMapping
    public void deleteSpecialtyById(@PathVariable("id") Long id){
        iSpecialtyService.DeleteById(id);
    }

    @PostMapping("/create")
    public void createSpecialty(@RequestBody Specialty specialty){
        iSpecialtyService.update(specialty);
    }

    @PostMapping("/update")
    public void updateSpecialty(@RequestBody Specialty specialty){
        iSpecialtyService.update(specialty);
    }
}
