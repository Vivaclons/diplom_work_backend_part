package kz.spring.analysisservice.repository;

import kz.spring.analysisservice.model.MedCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedCenterRepository extends JpaRepository<MedCenter, Long> {
}
