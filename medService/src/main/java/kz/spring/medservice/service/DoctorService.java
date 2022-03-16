package kz.spring.medservice.service;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.model.Specialty;
import kz.spring.medservice.repository.DoctorRepository;
import kz.spring.medservice.repository.SpecialtyRepository;
import kz.spring.medservice.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.swap;

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

    @Override
    public List<Doctor> nearDoctor(String lat, String lon){
        double latd = Double.parseDouble(lat);
        double lond = Double.parseDouble(lon);
        double raduis = 6371.01;

        List<Doctor> doctor = doctorRepository.findAll();

        boolean sort = true;

        for(int i = 0; i < doctor.size(); i++){
            double latd2 = Double.parseDouble(doctor.get(i).getLatitude());
            double lond2 = Double.parseDouble(doctor.get(i).getLongitude());

            double latDistance = Math.toRadians(latd - latd2);
            double lonDistance = Math.toRadians(lond - lond2);

            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(latd)) * Math.cos(Math.toRadians(latd2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            //exchange to meter
            double distance = raduis * c * 1000;

            distance = Math.pow(distance, 2);

            doctor.get(i).setDistance(Math.sqrt(distance));
        }

        while(sort){
            sort = false;
            for(int i = 1; i < doctor.size(); i++){
                if(doctor.get(i).getDistance() < doctor.get(i-1).getDistance()){
                    swap(doctor, i, i-1);
                    sort = true;
                }
            }
        }

        return doctor;
    }
}
