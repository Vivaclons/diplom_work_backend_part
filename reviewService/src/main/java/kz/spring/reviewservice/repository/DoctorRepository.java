package kz.spring.reviewservice.repository;

import kz.spring.reviewservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor getDoctorByDoctorId(Long id);
}
