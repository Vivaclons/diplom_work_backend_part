package kz.spring.analysisservice.service.impl;

import kz.spring.analysisservice.model.Customer;
import kz.spring.analysisservice.model.CustomerAnalysis;

import java.util.List;

public interface ICustomerAnalysisService {
    CustomerAnalysis removeCustomerAnalysis(Long customerAnalysisId, Long medCenterId, Long analysisId, Long doctorId, Long customerId);
    CustomerAnalysis addCustomerAnalysis(Long customerAnalysisId, Long medCenterId, Long analysisId, Long doctorId, Long customerId);
    CustomerAnalysis updateCustomerAnalysis(Long customerAnalysisId, Long medCenterId, Long analysisId, Long doctorId, Long customerId);
    List<CustomerAnalysis> getAllCustomerAnalysis();
}
