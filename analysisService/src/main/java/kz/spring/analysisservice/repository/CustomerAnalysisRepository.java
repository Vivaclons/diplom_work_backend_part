package kz.spring.analysisservice.repository;

import kz.spring.analysisservice.model.CustomerAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAnalysisRepository extends JpaRepository<CustomerAnalysis, Long> {
    CustomerAnalysis getCustomerAnalysisByCustomerAnalysisId(Long id);
    CustomerAnalysis findCustomerAnalysisByCustomerEmail(String email);
//    List<CustomerAnalysis> findByCustomerAnalysisId(Long id);
//    List<CustomerAnalysis> findCustomerAnalysisByCustomerAnalysisName(String customerAnalysisName);
//    List<CustomerAnalysis> getCustomerAnalysisByAnalysis_Description(String customerAnalysisName);
}
