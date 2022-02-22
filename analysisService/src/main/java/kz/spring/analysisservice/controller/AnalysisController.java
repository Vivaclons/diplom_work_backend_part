package kz.spring.analysisservice.controller;

import kz.spring.analysisservice.model.Analysis;
import kz.spring.analysisservice.service.impl.IAnalysisService;
//import kz.spring.medservice.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    private IAnalysisService iAnalysisService;

    @GetMapping("/all")
    public List<Analysis> getAllAnalysis(){
        return iAnalysisService.getAllAnalysis();
    }

    @GetMapping("/{id}")
    public Analysis getAnalysisById(@PathVariable("id") Long id){
        return iAnalysisService.getById(id);
    }

    @DeleteMapping("/delete/{analysis_id}")
    public void deleteAnalysisByID(@PathVariable("analysis_id") Long analysis_id){
        iAnalysisService.DeleteById(analysis_id);
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
