package kz.spring.medservice.service.impl;

import kz.spring.medservice.model.Doctor;
import java.util.List;

public interface IDoctorService {
    List<Doctor> searchDoctorByDoctorName(String doctorName);

    Doctor removeSpecialty(Long doctorId, Long specialtyId);

    Doctor addSpecialty(Long doctorId, Long specialtyId);

    Doctor updateSpecialty(Long doctorId, Long specialtyId);

    Doctor getByDoctorName(String doctorName);

    List<Doctor> searchSpecialtyDoctor(String specialty);

    Doctor createDoctor(Doctor doctor);

    List<Doctor> getAllDoctorByWorkTime(String date);

    void update(Doctor doctor);

    List<Doctor> getAllDoctor();

    void DeleteById(Long id);

    Doctor getDoctorById(Long id);

    List<Doctor> nearDoctor(String lat, String lon);

    void rating(Long doctorId, double docRat);

    boolean checkSpecialty(Doctor doctor, Long specialtyId);
}
