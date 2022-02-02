package kz.spring.analysisservice.controller;

import kz.spring.analysisservice.model.Analysis;
import kz.spring.analysisservice.service.impl.IAnalysisService;
import kz.spring.medservice.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    private IAnalysisService iAnalysisService;

    @GetMapping("")
    public List<Analysis> getAllAnalysis(){
        return iAnalysisService.getAllAnalysisBy();
    }

    @GetMapping("/{id}")
    public Analysis getAnalysisById(@PathVariable("id") Long id){
        return iAnalysisService.getById(id);
    }

    @DeleteMapping
    public void deleteAnalysisByID(@PathVariable("id") Long id){
        iAnalysisService.DeleteById(id);
    }

    @PostMapping("/create")
    public void createAnalysis(@RequestBody Analysis analysis){
        iAnalysisService.update(analysis);
    }

    @PutMapping("/update")
    public void updateAnalysis(@RequestBody Analysis analysis){
        iAnalysisService.update(analysis);
    }
}
