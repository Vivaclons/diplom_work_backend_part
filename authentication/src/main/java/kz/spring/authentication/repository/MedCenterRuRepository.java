package kz.spring.authentication.repository;

import kz.spring.authentication.model.MedCenterRu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedCenterRuRepository extends JpaRepository<MedCenterRu, Long> {

    MedCenterRu getByMedCenterName(String customerName);

    MedCenterRu findByActivationCode(String code);

    MedCenterRu findByMedCenterEmail(String email);

    MedCenterRu findByUsername(String username);

}
