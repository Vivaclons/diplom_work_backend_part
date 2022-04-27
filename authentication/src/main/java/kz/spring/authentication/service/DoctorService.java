package kz.spring.authentication.service;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Doctor;
import kz.spring.authentication.model.DoctorKz;
import kz.spring.authentication.model.DoctorRu;
import kz.spring.authentication.repository.DoctorKzRepository;
import kz.spring.authentication.repository.DoctorRepository;
import kz.spring.authentication.repository.DoctorRuRepository;
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
    private DoctorKzRepository doctorKzRepository;

    @Autowired
    private DoctorRuRepository doctorRuRepository;

    @Autowired
    private MailDelivery mailDelivery;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.getById(doctorId);
    }

    @Override
    public DoctorKz getDoctorKzByUsername(String username) {
        return doctorKzRepository.findByUsername(username);
    }

    @Override
    public DoctorRu getDoctorRuByUsername(String username) {
        return doctorRuRepository.findByUsername(username);
    }

    @Override
    public Doctor getByDoctorName(String doctorName) {
        return doctorRepository.getByDoctorName(doctorName);
    }

    @Override
    public DoctorRu getByDoctorRuName(String doctorName) {
        return doctorRuRepository.getByDoctorName(doctorName);
    }

    @Override
    public DoctorKz getByDoctorKzName(String doctorName) {
        return doctorKzRepository.getByDoctorName(doctorName);
    }

    @Override
    public void deleteDoctor(Long doctorId){
        doctorRepository.deleteById(doctorId);
        doctorKzRepository.deleteById(doctorId);
        doctorRuRepository.deleteById(doctorId);
    }

    @Override
    public void updateDoc(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public void updateDocKz(DoctorKz doctorKz) {
        doctorKz.setPassword(passwordEncoder.encode(doctorKz.getPassword()));
        doctorKzRepository.saveAndFlush(doctorKz);
    }

    @Override
    public void updateDocRu(DoctorRu doctorRu) {
        doctorRu.setPassword(passwordEncoder.encode(doctorRu.getPassword()));
        doctorRuRepository.saveAndFlush(doctorRu);
    }

    @Override
    public List<Doctor> getAllDoctorsBy() {
        return doctorRepository.getDoctorsBy();
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        Doctor doctor1 = doctorRepository.findByUsername(doctor.getUsername());
        DoctorRu doctorRu = new DoctorRu();
        DoctorKz doctorKz = new DoctorKz();

        if(doctor1 != null && doctorKz != null && doctorRu != null){
            System.out.println("ERROR");
            return false;
        }

        doctor.setStatus(true);
        doctorKz.setStatus(true);
        doctorRu.setStatus(true);

        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorKz.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorRu.setPassword(passwordEncoder.encode(doctor.getPassword()));

        doctor.setActivationCode(UUID.randomUUID().toString());
        doctorKz.setActivationCode(UUID.randomUUID().toString());
        doctorRu.setActivationCode(UUID.randomUUID().toString());

        doctor.setPeopleCount(50);
        doctorKz.setPeopleCount(50);
        doctorRu.setPeopleCount(50);

        doctor.setRating(5.0);
        doctorKz.setRating(5.0);
        doctorRu.setRating(5.0);

        doctorRu.setDoctorEmail(doctor.getDoctorEmail());
        doctorKz.setDoctorEmail(doctor.getDoctorEmail());


        if(!StringUtils.isEmpty(doctor.getDoctorEmail())){
            String message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/doctor/activate/%s",
                    doctor.getUsername(),
                    doctor.getActivationCode()
            );
            mailDelivery.send(doctor.getDoctorEmail(), "Activation code", message);
        }

        doctorRepository.saveAndFlush(doctor);
        doctorKzRepository.saveAndFlush(doctorKz);
        doctorRuRepository.saveAndFlush(doctorRu);
        return true;
    }

    @Override
    public boolean activateDoctor(String code) {

        Doctor doctor = doctorRepository.findByActivationCode(code);
        DoctorKz doctorKz = doctorKzRepository.findByActivationCode(code);
        DoctorRu doctorRu = doctorRuRepository.findByActivationCode(code);

        if(doctor == null && doctorKz == null && doctorRu == null){
            return false;
        }

        doctor.setActivationCode(null);
        doctorKz.setActivationCode(null);
        doctorRu.setActivationCode(null);
        doctorRepository.saveAndFlush(doctor);
        doctorKzRepository.saveAndFlush(doctorKz);
        doctorRuRepository.saveAndFlush(doctorRu);

        return true;
    }

    @Override
    public String forgetPasswordDoctor(String email){
        String message = "";

        Doctor doctor = doctorRepository.findByDoctorEmail(email);
        DoctorKz doctorKz = doctorKzRepository.findByDoctorEmail(email);
        DoctorRu doctorRu = doctorRuRepository.findByDoctorEmail(email);

        if(doctor != null && doctorKz != null && doctorRu != null){
            System.out.println("ERROR");
            return "ERROR with change password";
        }

        doctor.setActivationCode("forget password");
        doctorKz.setActivationCode("forget password");
        doctorRu.setActivationCode("forget password");

        if(!StringUtils.isEmpty(doctor.getDoctorEmail())){
            message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/doctor/activate/%s",
                    doctor.getUsername(),
                    doctor.getActivationCode()
            );
            mailDelivery.send(doctor.getDoctorEmail(), "Activation code", message);
        }

        doctorRepository.saveAndFlush(doctor);
        doctorRuRepository.saveAndFlush(doctorRu);
        doctorKzRepository.saveAndFlush(doctorKz);

        return message;
    }

    @Override
    public void updatePasswordDoctor(String email, String password) {

        Doctor doctor = doctorRepository.findByDoctorEmail(email);
        DoctorRu doctorRu = doctorRuRepository.findByDoctorEmail(email);
        DoctorKz doctorKz = doctorKzRepository.findByDoctorEmail(email);

        if(doctor != null && doctor != null && doctor != null){
            System.out.println("ERROR");
        }

        doctor.setPassword(passwordEncoder.encode(password));
        doctorKz.setPassword(passwordEncoder.encode(password));
        doctorRu.setPassword(passwordEncoder.encode(password));

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
}
