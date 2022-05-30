package kz.spring.authentication.service;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.repository.DoctorRepository;
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
        Doctor doctor1 = doctorRepository.findByUsername(doctor.getUsername());

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

            updateDoctor.setDoctorName(doctor.getDoctorName());
            updateDoctor.setDoctorSurname(doctor.getDoctorSurname());
            if(doctor.getDoctorEmail() != null){
                updateDoctor.setDoctorEmail(doctor.getDoctorEmail());
            }
            updateDoctor.setAddress(doctor.getAddress());
            updateDoctor.setAbout(doctor.getAbout());
            updateDoctor.setFees(doctor.getFees());
            updateDoctor.setExperience(doctor.getExperience());
            updateDoctor.setQualifications(doctor.getQualifications());
            updateDoctor.setWorkTimeFrom(doctor.getWorkTimeFrom());
            updateDoctor.setWorkTimeTo(doctor.getWorkTimeTo());

            doctorRepository.saveAndFlush(updateDoctor);

        }else{
            System.out.println("Doctor is empty!");
        }

    }
}
