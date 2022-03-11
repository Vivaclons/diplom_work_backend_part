package kz.spring.clientservice.repository;

import kz.spring.clientservice.model.Customer;
import kz.spring.clientservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor getDoctorByDoctorId(Long id);
}
