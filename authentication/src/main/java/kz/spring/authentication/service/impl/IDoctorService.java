package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IDoctorService {
    Doctor getDoctorById(Long doctorId);

    Doctor getByDoctorName(String doctorName);

    void updateDoc(Doctor customer);

    List<Doctor> getAllDoctorsBy();

    void DeleteByIDDoc(Long doctorId);

    boolean addDoctor(Doctor doctor);

    boolean activateDoctor(String code);

}
