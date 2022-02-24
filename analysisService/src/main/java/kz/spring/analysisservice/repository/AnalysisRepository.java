package kz.spring.analysisservice.repository;

import kz.spring.analysisservice.model.Analysis;
import kz.spring.analysisservice.model.CustomerAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
    List<Analysis> findByAnalysisId(Long id);
    List<Analysis> getAnalysisByDescriptionIsLike(String analysisDescription);
    Analysis getAnalysisByAnalysisId(Long id);
}
