package kz.spring.authentication.repository;

import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.model.DoctorKz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorKzRepository extends JpaRepository<DoctorKz, Long> {
    DoctorKz getByDoctorName(String customerName);

    DoctorKz findByActivationCode(String code);

    DoctorKz findByDoctorEmail(String email);

    DoctorKz findByUsername(String username);


}
