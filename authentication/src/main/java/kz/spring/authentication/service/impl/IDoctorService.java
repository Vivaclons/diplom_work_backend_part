package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.Doctor;

import java.util.List;

public interface IDoctorService {
    Doctor getDoctorById(Long doctorId);

    Doctor getByDoctorName(String doctorName);

    void updateDoc(Doctor customer);

    List<Doctor> getAllDoctorsBy();

    void deleteDoctor(Long doctorId);

    boolean addDoctor(Doctor doctor);

    boolean activateDoctor(String code);

    String forgetPasswordDoctor(String email);

    void updatePasswordDoctor(String email, String password);

}
