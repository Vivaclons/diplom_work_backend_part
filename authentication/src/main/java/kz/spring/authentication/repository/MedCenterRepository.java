package kz.spring.authentication.repository;

import kz.spring.authentication.model.MedCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedCenterRepository extends JpaRepository<MedCenter, Long> {
    MedCenter getById (Long medCenterId);
    MedCenter getByMedCenterName(String medCenterName);
    List<MedCenter> getMedCentersBy();
    MedCenter findByUsername(String username);
    MedCenter findByMedCenterTelNumber(String telNumber);
    MedCenter findByActivationCode(String code);
    MedCenter findMedCenterByMedCenterEmail(String email);
}
