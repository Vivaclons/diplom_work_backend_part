package kz.spring.analysisservice.service;

import kz.spring.analysisservice.model.*;
import kz.spring.analysisservice.repository.*;
import kz.spring.analysisservice.service.impl.ICustomerAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAnalysisService implements ICustomerAnalysisService {

    @Autowired
    private CustomerAnalysisRepository customerAnalysisRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MedCenterRepository medCenterRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AnalysisRepository analysisRepository;

    @Override
    public CustomerAnalysis removeCustomerAnalysis(Long customerAnalysisId, Long medCenterId, Long analysisId, Long doctorId, Long customerId) {
        return null;
    }

    @Override
    public CustomerAnalysis addCustomerAnalysis(Long customerAnalysisId, Long medCenterId, Long analysisId, Long doctorId, Long customerId) {

        CustomerAnalysis customerAnalysis = customerAnalysisRepository.getById(customerAnalysisId);
//
//        MedCenter medCenter = medCenterRepository.getById(medCenterId);
//
//        Doctor doctor = doctorRepository.getById(doctorId);
//
//        Customer customer = customerRepository.getById(customerId);
//
//        Analysis analysis = analysisRepository.getById(analysisId);
//
//        boolean check = false;
//
//        if(doctor != null && medCenter != null && customer != null && analysis != null && customerAnalysis != null){
//            customerAnalysis.getAnalysis().setAnalysisId(analysisId);
//            customerAnalysis.getCustomer().setCustomerId(customerId);
//            customerAnalysis.getDoctor().setDoctorId(doctorId);
//            customerAnalysis.getMedCenter().setMedCenterId(medCenterId);
//            if(!check){
//                return customerAnalysisRepository.saveAndFlush(customerAnalysis);
//            }
//        }
        return null;
    }

    @Override
    public CustomerAnalysis updateCustomerAnalysis(Long customerAnalysisId, Long medCenterId, Long analysisId, Long doctorId, Long customerId) {

//        CustomerAnalysis customerAnalysis = customerAnalysisRepository.getById(customerAnalysisId);
//
//        if(customerAnalysis != null && customerAnalysis.getCustomerAnalysis_id() != null && customerAnalysis.getCustomerAnalysis_id() != 0L) {
//            for (int i = 0; i < customerAnalysis.getAnalysis().getObjectSize(); i++) {
//                customerAnalysis.getAnalysis().setAnalysisId(analysisId);
//                return customerAnalysisRepository.saveAndFlush(customerAnalysis);
//            }
//        }
        return null;
    }

    @Override
    public List<CustomerAnalysis> getAllCustomerAnalysis() {
        return this.customerAnalysisRepository.findAll();
    }
}
