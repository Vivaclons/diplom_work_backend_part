package kz.spring.analysisservice.repository;

import kz.spring.analysisservice.model.Customer;
import kz.spring.analysisservice.model.CustomerAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAnalysisRepository extends JpaRepository<Customer, Long> {
    List<CustomerAnalysis> findByCustomerAnalysisId(Long id);
    List<CustomerAnalysis> findCustomerAnalysisByCustomerAnalysisName(String customerAnalysisName);
    List<CustomerAnalysis> getCustomerAnalysisByCustomerAnalysisNameIsLike(String customerAnalysisName);
}
