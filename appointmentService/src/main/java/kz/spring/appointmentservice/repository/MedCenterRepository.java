package kz.spring.appointmentservice.repository;

import kz.spring.appointmentservice.model.MedCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedCenterRepository extends JpaRepository<MedCenter, Long> {
    MedCenter getMedCenterByMedCenterId(Long id);
}
