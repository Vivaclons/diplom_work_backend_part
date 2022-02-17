package kz.spring.analysisservice.repository;

import kz.spring.analysisservice.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
    Analysis getById(Long id);
}
