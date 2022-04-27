package kz.spring.authentication.repository;

import kz.spring.authentication.model.DoctorKz;
import kz.spring.authentication.model.MedCenter;
import kz.spring.authentication.model.MedCenterKz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedCenterKzRepository extends JpaRepository<MedCenterKz, Long> {
    MedCenterKz getByMedCenterName(String customerName);

    MedCenterKz findByActivationCode(String code);

    MedCenterKz findByMedCenterEmail(String email);

    MedCenterKz findByUsername(String username);
}
