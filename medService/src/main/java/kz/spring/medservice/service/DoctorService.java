package kz.spring.medservice.service;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.model.Specialty;
import kz.spring.medservice.repository.DoctorRepository;
import kz.spring.medservice.repository.SpecialtyRepository;
import kz.spring.medservice.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

//    @Override
//    public List<Doctor> searchDoctorsBySpecialty(String specialtyName) {
//        return doctorRepository.getDoctorsBySpecialties();
//    }

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
    public Doctor getByDoctorName(String doctorName) {
        return doctorRepository.getDoctorByDoctorName(doctorName);
    }

//    public List<Doctor> getDoctorBySpecialty(String specialty) {
//        return doctorRepository.getDoctorsBySpecialties("%" + specialty + "%");
//    }

    @Override
    public List<Doctor> searchSpecialtyDoctor(String specialtyName){
        List<Doctor> doctor = doctorRepository.findAll();

        List<Doctor> doctors = new ArrayList<>();

        for (int i = 0; i < doctor.size(); i++){
            for(int k = 0; k < doctor.get(i).getSpecialties().size(); k++){
                if(doctor.get(i).getSpecialties().get(k).getSpecialtyName().equals(specialtyName)){
                    doctors.add(doctor.get(i));
                }
            }
        }

        return doctors;
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

    @Override
    public List<Doctor> getAllDoctorByWorkTime(String date){

        List<Doctor> doctor = doctorRepository.findAll();

        List<Doctor> doctorTime = new ArrayList<>();

        int timeNow = Integer.parseInt(date.replace(':', '0'));
        int timeFrom = 0;
        int timeTo = 0;

        for(int i = 0; i < doctor.size(); i++){
            timeFrom = Integer.parseInt(doctor.get(i).getWorkTimeFrom().replace(':', '0'));
            timeTo = Integer.parseInt(doctor.get(i).getWorkTimeTo().replace(':', '0'));

            if(timeNow > timeFrom && timeNow < timeTo){
                doctorTime.add(doctor.get(i));
            }

        }
        return doctorTime;
    }
}
