package kz.spring.medservice.repository;

import kz.spring.medservice.model.MedCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedCenterRepository extends JpaRepository<MedCenter, Long> {
    MedCenter getByMedCenterId(Long id);
    MedCenter getById(Long id);
    MedCenter getMedCenterByMedCenterName(String medCenterName);
    MedCenter getMedCenterByMedCenterAddress(String address);
}
