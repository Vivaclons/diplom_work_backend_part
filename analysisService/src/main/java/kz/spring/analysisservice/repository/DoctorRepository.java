package kz.spring.analysisservice.repository;

import kz.spring.analysisservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor getDoctorByDoctorId(Long id);
}
