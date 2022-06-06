package kz.spring.authentication.service;

import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.model.Specialty;
import kz.spring.authentication.repository.DoctorRepository;
import kz.spring.authentication.repository.SpecialtyRepository;
import kz.spring.authentication.service.impl.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorService implements UserDetailsService, IDoctorService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MailDelivery mailDelivery;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.getById(doctorId);
    }

    @Override
    public Doctor getByDoctorName(String doctorName) {
        return doctorRepository.getByDoctorName(doctorName);
    }

    @Override
    public void deleteDoctor(Long doctorId){
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public void updateDoc(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public List<Doctor> getAllDoctorsBy() {
        return doctorRepository.getDoctorsBy();
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        Doctor doctor1 = doctorRepository.findDoctorByDoctorEmail(doctor.getDoctorEmail());

        if(doctor1 != null){
            System.out.println("ERROR");
            return false;
        }

        doctor.setStatus(true);

        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));

        doctor.setActivationCode(UUID.randomUUID().toString());

        doctor.setPeopleCount(50);

        doctor.setRating(5.0);

        if(!StringUtils.isEmpty(doctor.getDoctorEmail())){
            String message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/doctor/activate/%s",
                    doctor.getUsername(),
                    doctor.getActivationCode()
            );
            mailDelivery.send(doctor.getDoctorEmail(), "Activation code", message);
        }

        doctorRepository.saveAndFlush(doctor);
        return true;
    }

    @Override
    public boolean activateDoctor(String code) {

        Doctor doctor = doctorRepository.findByActivationCode(code);

        if(doctor == null){
            return false;
        }

        doctor.setActivationCode(null);
        doctorRepository.saveAndFlush(doctor);

        return true;
    }

    @Override
    public String forgetPasswordDoctor(String email){
        String message = "";

        Doctor doctor = doctorRepository.findDoctorsByDoctorEmail(email);

        if(doctor != null){
            System.out.println("ERROR");
            return "ERROR with change password";
        }

        doctor.setActivationCode("forget password");

        if(!StringUtils.isEmpty(doctor.getDoctorEmail())){
            message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/doctor/activate/%s",
                    doctor.getUsername(),
                    doctor.getActivationCode()
            );
            mailDelivery.send(doctor.getDoctorEmail(), "Activation code", message);
        }

        doctorRepository.saveAndFlush(doctor);

        return message;
    }

    @Override
    public void updatePasswordDoctor(String email, String password) {

        Doctor doctor = doctorRepository.findDoctorsByDoctorEmail(email);

        if(doctor != null){
            System.out.println("ERROR");
        }

        doctor.setPassword(passwordEncoder.encode(password));

        doctorRepository.saveAndFlush(doctor);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Doctor doctor = doctorRepository.findByUsername(username);

        if(doctor == null){
            throw new UsernameNotFoundException("Doctor by this userName: " + username + " not found!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        doctor.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        });

        return new User(doctor.getUsername(), doctor.getPassword(), authorities);
    }

    @Override
    public Doctor getDoctorByEmail(String email){
        Doctor doctor = doctorRepository.findDoctorByDoctorEmail(email);
        if(doctor == null){
            System.out.println("DOCTOR NOT FOUND!");
        }
        return doctor;
    }

    @Override
    public void update(Doctor doctor, String email) {

        Doctor updateDoctor = doctorRepository.findDoctorByDoctorEmail(email);

        if(updateDoctor != null){

            if(doctor.getDoctorName() != null){
                updateDoctor.setDoctorName(doctor.getDoctorName());
            }
            if(doctor.getDoctorSurname() != null){
                updateDoctor.setDoctorSurname(doctor.getDoctorSurname());
            }
            if(doctor.getDoctorEmail() != null){
                updateDoctor.setDoctorEmail(doctor.getDoctorEmail());
            }
            if(doctor.getAddress() != null){
                updateDoctor.setAddress(doctor.getAddress());
            }
            if(doctor.getAbout() != null){
                updateDoctor.setAbout(doctor.getAbout());
            }
            if(doctor.getFees() != null){
                updateDoctor.setFees(doctor.getFees());
            }
            if(doctor.getExperience() != null){
                updateDoctor.setExperience(doctor.getExperience());
            }
            if(doctor.getQualifications() != null){
                updateDoctor.setQualifications(doctor.getQualifications());
            }
            if(doctor.getWorkTimeFrom() != null){
                updateDoctor.setWorkTimeFrom(doctor.getWorkTimeFrom());
            }
            if(doctor.getWorkTimeTo() != null){
                updateDoctor.setWorkTimeTo(doctor.getWorkTimeTo());
            }

            doctorRepository.saveAndFlush(updateDoctor);

        }else{
            System.out.println("Doctor is empty!");
        }

    }

    @Override
    public Doctor addSpecialty(String email, Long specialtyId) {

        Doctor doctor = doctorRepository.findDoctorByDoctorEmail(email);

        Specialty specialty = specialtyRepository.getSpecialtyBySpecialtyId(specialtyId);

        boolean check = false;

        if(doctor != null && specialty != null && checkSpecialty(doctor, specialtyId)){
            doctor.getSpecialties().add(specialty);
            if(!check){
                return doctorRepository.saveAndFlush(doctor);
            }
        }
        return null;
    }

    @Override
    public Doctor updateSpecialty(String email, Long specialtyId) {

        Doctor doctor = doctorRepository.findDoctorByDoctorEmail(email);

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
    public Doctor removeSpecialty(String email, Long specialtyId) {

        Doctor doctor = doctorRepository.findDoctorByDoctorEmail(email);

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
    public boolean checkSpecialty(Doctor doctor, Long specialtyId){
        for(int i = 0; i < doctor.getSpecialties().size(); i++){
            if(doctor.getSpecialties().get(i).getSpecialtyId() == specialtyId){
                System.out.println("doctor has this specialty " + doctor.getSpecialties().get(i).getSpecialtyName());
                return false;
            }
        }
        return true;
    }

}
