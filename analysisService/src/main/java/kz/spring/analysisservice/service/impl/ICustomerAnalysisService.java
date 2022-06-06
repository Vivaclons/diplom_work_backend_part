package kz.spring.analysisservice.service.impl;

import kz.spring.analysisservice.model.Analysis;
import kz.spring.analysisservice.model.Customer;
import kz.spring.analysisservice.model.CustomerAnalysis;

import java.util.List;

public interface ICustomerAnalysisService {
    void removeCustomerAnalysis(Long customerAnalysisId);
    CustomerAnalysis addCustomerAnalysis(Long customerAnalysisId, Long analysisId, Long doctorId, Long customerId);
    CustomerAnalysis updateCustomerAnalysis(Long customerAnalysisId, Long medCenterId, Long analysisId, Long doctorId, Long customerId);
    List<CustomerAnalysis> getAllCustomerAnalysis(String email);
    CustomerAnalysis updateCustomer(Long customerAnalysisId, Long customerId);
    CustomerAnalysis updateDoctor(Long customerAnalysisId, Long doctorId);
    CustomerAnalysis updateMedCenter(Long customerAnalysisId, Long medCenterId);
    CustomerAnalysis updateAnalysis(Long customerAnalysisId, Long analysisId);
    CustomerAnalysis createCusAnalysis(Analysis analysis, String doctorEmail, String customerEmail);
}
