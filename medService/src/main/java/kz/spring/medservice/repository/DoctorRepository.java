package kz.spring.medservice.repository;

import kz.spring.medservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor getByDoctorId(Long id);
    Doctor getById(Long id);
    Doctor getDoctorByDoctorName(String doctorName);
}
