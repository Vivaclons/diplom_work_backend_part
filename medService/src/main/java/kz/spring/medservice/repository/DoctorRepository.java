package kz.spring.medservice.repository;

import kz.spring.medservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByDoctorId(Long id);
    Doctor findDoctorByDoctorEmail(String email);
    List<Doctor> findDoctorByDoctorName(String doctorName);
    List<Doctor> getDoctorByDoctorNameIsLike(String doctorName);
    Doctor getDoctorByDoctorId(Long id);
    Doctor getDoctorByDoctorName(String doctorName);
//    List<Doctor> getDoctorsBy(String specialties);
//    List<Doctor> getDoctorsBySpecialties(String specialties);
}
