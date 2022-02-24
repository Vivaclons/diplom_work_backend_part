package kz.spring.analysisservice.controller;

import kz.spring.analysisservice.model.CustomerAnalysis;
import kz.spring.analysisservice.service.impl.ICustomerAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerAnalysis")
public class CustomerAnalysisController {
    @Autowired
    private ICustomerAnalysisService iCustomerAnalysisService;

    @GetMapping("/all")
    public List<CustomerAnalysis> getAllCustomerAnalysis(){
        return iCustomerAnalysisService.getAllCustomerAnalysis();
    }

    @PatchMapping("/add-customer-analysis/{customerAnalysisId}/{medCenterId}/{analysisId}/{doctorId}/{customerId}")
    public ResponseEntity<?> addCustomerAnalysis(@PathVariable("customerAnalysisId") Long customerAnalysisId, @PathVariable("medCenterId") Long medCenterId, @PathVariable("analysisId") Long analysisId, @PathVariable("doctorId") Long doctorId, @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(iCustomerAnalysisService.addCustomerAnalysis(customerAnalysisId, medCenterId, analysisId, doctorId, customerId));
    }

    @PatchMapping("/update-customer/{customerAnalysisId}/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable("customerAnalysisId") Long customerAnalysisId, @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(iCustomerAnalysisService.updateCustomer(customerAnalysisId, customerId));
    }

    @PatchMapping("/update-customer/{customerAnalysisId}/{analysisId}")
    public ResponseEntity<?> updateAnalysis(@PathVariable("customerAnalysisId") Long customerAnalysisId, @PathVariable("analysisId") Long analysisId) {
        return ResponseEntity.ok(iCustomerAnalysisService.updateAnalysis(customerAnalysisId, analysisId));
    }

    @PatchMapping("/update-customer/{customerAnalysisId}/{doctorId}")
    public ResponseEntity<?> updateDoctor(@PathVariable("customerAnalysisId") Long customerAnalysisId, @PathVariable("doctorId") Long doctorId) {
        return ResponseEntity.ok(iCustomerAnalysisService.updateCustomer(customerAnalysisId, doctorId));
    }

    @PatchMapping("/update-customer/{customerAnalysisId}/{medCenterId}")
    public ResponseEntity<?> updateMedCenter(@PathVariable("customerAnalysisId") Long customerAnalysisId, @PathVariable("medCenterId") Long medCenterId) {
        return ResponseEntity.ok(iCustomerAnalysisService.updateMedCenter(customerAnalysisId, medCenterId));
    }

    @PatchMapping("/update-customer-analysis/{customerAnalysisId}/{medCenterId}/{analysisId}/{doctorId}/{customerId}")
    public ResponseEntity<?> updateMedCenter(@PathVariable("customerAnalysisId") Long customerAnalysisId, @PathVariable("medCenterId") Long medCenterId, @PathVariable("analysisId") Long analysisId, @PathVariable("doctorId") Long doctorId, @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(iCustomerAnalysisService.updateCustomerAnalysis(customerAnalysisId, medCenterId, analysisId, doctorId, customerId));
    }

    @DeleteMapping("/delete/{customerAnalysisId}")
    public void deleteCustomerAnalysis(@PathVariable("customerAnalysisId") Long customerAnalysisId){
        iCustomerAnalysisService.removeCustomerAnalysis(customerAnalysisId);
    }
}
