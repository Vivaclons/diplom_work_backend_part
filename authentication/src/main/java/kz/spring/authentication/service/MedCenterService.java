package kz.spring.authentication.service;

import kz.spring.authentication.model.*;
import kz.spring.authentication.repository.MedCenterRepository;
import kz.spring.authentication.service.impl.IMedCenterService;
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
public class MedCenterService implements UserDetailsService, IMedCenterService {

    @Autowired
    private MedCenterRepository medCenterRepository;

    @Autowired
    private MailDelivery mailDelivery;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public MedCenter getMedCenterById(Long medCenterId) {
        return medCenterRepository.getById(medCenterId);
    }

    @Override
    public MedCenter getByMedCenterName(String medCenterName) {
        return  medCenterRepository.getByMedCenterName(medCenterName);
    }

    @Override
    public void updateMed(MedCenter medCenter) {
        medCenter.setPassword(passwordEncoder.encode(medCenter.getPassword()));
        medCenterRepository.saveAndFlush(medCenter);
    }

    @Override
    public List<MedCenter> getAllMedCentersBy() {
        return medCenterRepository.getMedCentersBy();
    }

    @Override
    public void DeleteByIDMed(Long medCenterId) {
        medCenterRepository.deleteById(medCenterId);
    }

    @Override
    public boolean addMedCenter(MedCenter medCenter) {
        MedCenter medCenter1 = medCenterRepository.findByUsername(medCenter.getUsername());

        if(medCenter1 != null){
            System.out.println("ERROR");
            return false;
        }

        medCenter.setStatus(true);

        medCenter.setPassword(passwordEncoder.encode(medCenter.getPassword()));

        medCenter.setActivationCode(UUID.randomUUID().toString());

        medCenter.setPeopleCount(50);

        medCenter.setRating(5.0);

        if(!StringUtils.isEmpty(medCenter.getMedCenterEmail())){
            String message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/doctor/activate/%s",
                    medCenter.getUsername(),
                    medCenter.getActivationCode()
            );
            mailDelivery.send(medCenter.getMedCenterEmail(), "Activation code", message);
        }

        medCenterRepository.saveAndFlush(medCenter);

        return true;
    }

    @Override
    public boolean activateMedCenter(String code) {

        MedCenter medCenter = medCenterRepository.findByActivationCode(code);

        if(medCenter == null){
            return false;
        }

        medCenter.setActivationCode(null);

        medCenterRepository.saveAndFlush(medCenter);

        return true;
    }


    @Override
    public void updatePasswordMedCenter(String email, String password) {
        MedCenter medCenter = medCenterRepository.findByMedCenterEmail(email);

        if(medCenter != null && medCenter != null && medCenter != null){
            System.out.println("ERROR");
        }

        medCenter.setPassword(passwordEncoder.encode(password));

        medCenterRepository.saveAndFlush(medCenter);
    }

    @Override
    public String forgetPasswordMedCenter(String email) {

        String message = "";

        MedCenter medCenter = medCenterRepository.findByMedCenterEmail(email);

        if(medCenter != null){
            System.out.println("ERROR");
            return "ERROR with change password";
        }

        medCenter.setActivationCode("forget password");

        if(!StringUtils.isEmpty(medCenter.getMedCenterEmail())){
            message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/medCenter/activate/%s",
                    medCenter.getUsername(),
                    medCenter.getActivationCode()
            );
            mailDelivery.send(medCenter.getMedCenterEmail(), "Activation code", message);
        }

        medCenterRepository.saveAndFlush(medCenter);

        return message;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MedCenter medCenter = medCenterRepository.findByUsername(username);

        if(medCenter == null){
            throw new UsernameNotFoundException("Medical center by this userName: " + username + " not found!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        medCenter.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        });

        return new User(medCenter.getUsername(), medCenter.getPassword(), authorities);
    }
}
