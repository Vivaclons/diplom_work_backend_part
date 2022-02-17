package kz.spring.medservice.service;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.repository.DoctorRepository;
import kz.spring.medservice.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService implements IDoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor getById(Long id) {
        return doctorRepository.getById(id);
    }

    @Override
    public Doctor getByDoctorName(String doctorName) {
        return doctorRepository.getDoctorByDoctorName(doctorName);
    }

    @Override
    public void update(Doctor doctor) {
        doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public void DeleteById(Long id) {
        doctorRepository.deleteById(id);
    }
}
