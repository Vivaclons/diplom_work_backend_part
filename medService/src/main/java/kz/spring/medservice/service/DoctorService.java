package kz.spring.medservice.service;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.model.Specialty;
import kz.spring.medservice.repository.DoctorRepository;
import kz.spring.medservice.repository.SpecialtyRepository;
import kz.spring.medservice.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService implements IDoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public List<Doctor> searchDoctorByDoctorName(String doctorName) {
        return doctorRepository.getDoctorByDoctorNameIsLike("%" + doctorName + "%");
    }

    @Override
    public Doctor removeSpecialty(Long doctorId, Long specialtyId) {

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        boolean check = false;

        for(int i = 0; i < doctor.getSpecialties().size(); i++){
            if(doctor.getSpecialties().get(i).getSpecialtyId().equals(specialtyId)){
                doctor.getSpecialties().remove(i);
                check = true;
                break;
            }
        }

        if(check){
            return doctorRepository.saveAndFlush(doctor);
        }
        return null;
    }

    @Override
    public Doctor addSpecialty(Long doctorId, Long specialtyId) {

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        Specialty specialty = specialtyRepository.getSpecialtyBySpecialtyId(specialtyId);

        boolean check = false;

        if(doctor != null && specialty != null){
            doctor.getSpecialties().add(specialty);
            if(!check){
                return doctorRepository.saveAndFlush(doctor);
            }
        }
        return null;
    }

    @Override
    public Doctor updateSpecialty(Long doctorId, Long specialtyId) {

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        if(doctor != null && doctor.getDoctorId() != null && doctor.getDoctorId() != 0L) {
            for (int i = 0; i < doctor.getSpecialties().size(); i++) {
                doctor.getSpecialties().get(i).setSpecialtyId(specialtyId);
                return doctorRepository.saveAndFlush(doctor);
            }
        }
        return null;
    }

    @Override
    public Doctor getByDoctorName(String doctorName) {
        return doctorRepository.getDoctorByDoctorName(doctorName);
    }

    @Override
    public Doctor getDoctorBySpecialty(String specialty) {
        return doctorRepository.getDoctorsBySpecialties(specialty);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.saveAndFlush(doctor);
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

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.getDoctorByDoctorId(id);
    }
}
