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
    public void removeCustomerAnalysis(Long customerAnalysisId) {
        customerAnalysisRepository.deleteById(customerAnalysisId);
    }

    @Override
    public CustomerAnalysis addCustomerAnalysis(Long customerAnalysisId, Long medCenterId, Long analysisId, Long doctorId, Long customerId) {

        CustomerAnalysis customerAnalysis = customerAnalysisRepository.getCustomerAnalysisByCustomerAnalysisId(customerAnalysisId);

        MedCenter medCenter = medCenterRepository.getMedCenterByMedCenterId(medCenterId);

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        Customer customer = customerRepository.getCustomerByCustomerId(customerId);

        Analysis analysis = analysisRepository.getAnalysisByAnalysisId(analysisId);

        boolean check = false;

        if(doctor != null && medCenter != null && customer != null && analysis != null && customerAnalysis != null){
            customerAnalysis.getAnalysis().setAnalysisId(analysisId);
            customerAnalysis.getCustomer().setCustomerId(customerId);
            customerAnalysis.getDoctor().setDoctorId(doctorId);
            customerAnalysis.getMedCenter().setMedCenterId(medCenterId);
            if(!check){
                return customerAnalysisRepository.saveAndFlush(customerAnalysis);
            }
        }
        return null;
    }

    @Override
    public CustomerAnalysis updateCustomer(Long customerAnalysisId, Long customerId){

//        customerAnalysisRepository.deleteCustomerAnalysisByCustomerCustomerId(customerId);

        CustomerAnalysis customerAnalysis = customerAnalysisRepository.getCustomerAnalysisByCustomerAnalysisId(customerAnalysisId);

        Customer customer = customerRepository.getCustomerByCustomerId(customerId);

        boolean check = false;

        if(customerAnalysis.getCustomer().getCustomerId().equals(customerId)){
                customerAnalysis.setCustomer(customer);
                check = true;
        }

        if(check){
            return customerAnalysisRepository.saveAndFlush(customerAnalysis);
        }
        return null;
    }

    @Override
    public CustomerAnalysis updateDoctor(Long customerAnalysisId, Long doctorId) {
        CustomerAnalysis customerAnalysis = customerAnalysisRepository.getCustomerAnalysisByCustomerAnalysisId(customerAnalysisId);

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        boolean check = false;

        if(customerAnalysis.getDoctor().getDoctorId().equals(doctorId)){
            customerAnalysis.setDoctor(doctor);
            check = true;
        }

        if(check){
            return customerAnalysisRepository.saveAndFlush(customerAnalysis);
        }
        return null;
    }

    @Override
    public CustomerAnalysis updateMedCenter(Long customerAnalysisId, Long medCenterId) {
        CustomerAnalysis customerAnalysis = customerAnalysisRepository.getCustomerAnalysisByCustomerAnalysisId(customerAnalysisId);

        MedCenter medCenter = medCenterRepository.getMedCenterByMedCenterId(medCenterId);

        boolean check = false;

        if(customerAnalysis.getMedCenter().getMedCenterId().equals(medCenterId)){
            customerAnalysis.setMedCenter(medCenter);
            check = true;
        }

        if(check){
            return customerAnalysisRepository.saveAndFlush(customerAnalysis);
        }
        return null;
    }

    @Override
    public CustomerAnalysis updateAnalysis(Long customerAnalysisId, Long analysisId) {
        CustomerAnalysis customerAnalysis = customerAnalysisRepository.getCustomerAnalysisByCustomerAnalysisId(customerAnalysisId);

        Analysis analysis = analysisRepository.getAnalysisByAnalysisId(analysisId);

        boolean check = false;

        if(customerAnalysis.getAnalysis().getAnalysisId().equals(analysisId)){
            customerAnalysis.setAnalysis(analysis);
            check = true;
        }

        if(check){
            return customerAnalysisRepository.saveAndFlush(customerAnalysis);
        }
        return null;
    }

    @Override
    public CustomerAnalysis updateCustomerAnalysis(Long customerAnalysisId, Long medCenterId, Long analysisId, Long doctorId, Long customerId) {

        CustomerAnalysis customerAnalysis = customerAnalysisRepository.getCustomerAnalysisByCustomerAnalysisId(customerAnalysisId);

        MedCenter medCenter = medCenterRepository.getMedCenterByMedCenterId(medCenterId);

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        Customer customer = customerRepository.getCustomerByCustomerId(customerId);

        Analysis analysis = analysisRepository.getAnalysisByAnalysisId(analysisId);

        boolean check = false;

        if(doctor != null && medCenter != null && customer != null && analysis != null && customerAnalysis != null){
            customerAnalysis.getAnalysis().setAnalysisId(analysisId);
            customerAnalysis.getCustomer().setCustomerId(customerId);
            customerAnalysis.getDoctor().setDoctorId(doctorId);
            customerAnalysis.getMedCenter().setMedCenterId(medCenterId);
            if(!check){
                return customerAnalysisRepository.saveAndFlush(customerAnalysis);
            }
        }
        return null;
    }

    @Override
    public List<CustomerAnalysis> getAllCustomerAnalysis() {
        return this.customerAnalysisRepository.findAll();
    }
}
