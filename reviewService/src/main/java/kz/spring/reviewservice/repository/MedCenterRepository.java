package kz.spring.reviewservice.repository;

import kz.spring.reviewservice.model.MedCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedCenterRepository extends JpaRepository<MedCenter, Long> {
    MedCenter findMedCenterByMedCenterId(Long id);
}
