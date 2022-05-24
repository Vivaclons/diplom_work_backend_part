package kz.spring.authentication.repository;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor getById (Long customerId);
    Doctor getByDoctorName(String customerName);
    List<Doctor> getDoctorsBy();
    Doctor findByUsername(String username);
    Doctor findByDoctorTelNumber(String telNumber);
    Doctor findByActivationCode(String code);
    Doctor findDoctorsByDoctorEmail(String email);
    Doctor findDoctorByDoctorEmail(String email);
}
