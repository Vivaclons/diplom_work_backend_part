package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.model.DoctorKz;
import kz.spring.authentication.model.DoctorRu;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

    DoctorRu getByDoctorRuName(String doctorName);

    DoctorKz getByDoctorKzName(String doctorName);

    void updateDocKz(DoctorKz doctorKz);

    void updateDocRu(DoctorRu doctorRu);

    DoctorKz getDoctorKzByUsername(String username);

    DoctorRu getDoctorRuByUsername(String username);
}
