package kz.spring.medservice.service.impl;

import kz.spring.medservice.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IDoctorService {
    Doctor getById(Long id);
    Doctor getByDoctorName(String doctorName);
    void update(Doctor doctor);
    List<Doctor> getAllDoctorBy();
    void DeleteById(Long id);
}
