package kz.spring.analysisservice.repository;

import kz.spring.analysisservice.model.CustomerAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAnalysisRepository extends JpaRepository<CustomerAnalysis, Long> {
    CustomerAnalysis getCustomerAnalysisByCustomerAnalysisId(Long id);
    CustomerAnalysis findCustomerAnalysisByCustomerEmail(String email);
//    List<CustomerAnalysis> findByCustomerAnalysisId(Long id);
//    List<CustomerAnalysis> findCustomerAnalysisByCustomerAnalysisName(String customerAnalysisName);
//    List<CustomerAnalysis> getCustomerAnalysisByAnalysis_Description(String customerAnalysisName);
}
