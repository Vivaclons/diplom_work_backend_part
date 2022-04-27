package kz.spring.authentication.repository;

import kz.spring.authentication.model.DoctorKz;
import kz.spring.authentication.model.DoctorRu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRuRepository extends JpaRepository<DoctorRu, Long> {
    DoctorRu getByDoctorName(String customerName);

    DoctorRu findByActivationCode(String code);

    DoctorRu findByDoctorEmail(String email);

    DoctorRu findByUsername(String username);

}
