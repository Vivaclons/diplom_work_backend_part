package kz.spring.authentication.service;

import kz.spring.authentication.model.*;
import kz.spring.authentication.repository.MedCenterKzRepository;
import kz.spring.authentication.repository.MedCenterRepository;
import kz.spring.authentication.repository.MedCenterRuRepository;
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
    private MedCenterKzRepository medCenterKzRepository;

    @Autowired
    private MedCenterRuRepository medCenterRuRepository;

    @Autowired
    private MailDelivery mailDelivery;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public MedCenter getMedCenterById(Long medCenterId) {
        return medCenterRepository.getById(medCenterId);
    }

    @Override
    public MedCenterKz getMedCenterKzByUsername(String username) {
        return medCenterKzRepository.findByUsername(username);
    }

    @Override
    public MedCenterRu getMedCenterRuByUsername(String username) {
        return medCenterRuRepository.findByUsername(username);
    }

    @Override
    public MedCenterKz getByMedCenterKzName(String customerName) {
        return medCenterKzRepository.getByMedCenterName(customerName);
    }

    @Override
    public MedCenterRu getByMedCenterRuName(String customerName) {
        return medCenterRuRepository.getByMedCenterName(customerName);
    }

    @Override
    public void updateMCKz(MedCenterKz medCenterKz){
        medCenterKz.setPassword(passwordEncoder.encode(medCenterKz.getPassword()));
        medCenterKzRepository.saveAndFlush(medCenterKz);
    }

    @Override
    public void updateMCRu(MedCenterRu medCenterRu){
        medCenterRu.setPassword(passwordEncoder.encode(medCenterRu.getPassword()));
        medCenterRuRepository.saveAndFlush(medCenterRu);
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
        medCenterKzRepository.deleteById(medCenterId);
        medCenterRuRepository.deleteById(medCenterId);
    }

    @Override
    public boolean addMedCenter(MedCenter medCenter) {
        MedCenter medCenter1 = medCenterRepository.findByUsername(medCenter.getUsername());
        MedCenterKz medCenterKz = medCenterKzRepository.findByUsername(medCenter.getUsername());
        MedCenterRu medCenterRu = medCenterRuRepository.findByUsername(medCenter.getUsername());

        if(medCenter1 != null && medCenterRu != null && medCenterKz != null){
            System.out.println("ERROR");
            return false;
        }

        medCenter.setStatus(true);
        medCenterKz.setStatus(true);
        medCenterRu.setStatus(true);

        medCenter.setPassword(passwordEncoder.encode(medCenter.getPassword()));
        medCenterKz.setPassword(passwordEncoder.encode(medCenter.getPassword()));
        medCenterRu.setPassword(passwordEncoder.encode(medCenter.getPassword()));

        medCenter.setActivationCode(UUID.randomUUID().toString());
        medCenterKz.setActivationCode(UUID.randomUUID().toString());
        medCenterRu.setActivationCode(UUID.randomUUID().toString());

        medCenter.setPeopleCount(50);
        medCenterKz.setPeopleCount(50);
        medCenterRu.setPeopleCount(50);

        medCenter.setRating(5.0);
        medCenterKz.setRating(5.0);
        medCenterRu.setRating(5.0);

        medCenterKz.setMedCenterEmail(medCenter.getMedCenterEmail());
        medCenterRu.setMedCenterEmail(medCenter.getMedCenterEmail());

        if(!StringUtils.isEmpty(medCenter.getMedCenterEmail())){
            String message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/doctor/activate/%s",
                    medCenter.getUsername(),
                    medCenter.getActivationCode()
            );
            mailDelivery.send(medCenter.getMedCenterEmail(), "Activation code", message);
        }

        medCenterRepository.saveAndFlush(medCenter);
        medCenterKzRepository.saveAndFlush(medCenterKz);
        medCenterRuRepository.saveAndFlush(medCenterRu);

        return true;
    }

    @Override
    public boolean activateMedCenter(String code) {

        MedCenter medCenter = medCenterRepository.findByActivationCode(code);
        MedCenterKz medCenterKz = medCenterKzRepository.findByActivationCode(code);
        MedCenterRu medCenterRu = medCenterRuRepository.findByActivationCode(code);

        if(medCenter == null && medCenterKz == null && medCenterRu == null){
            return false;
        }

        medCenter.setActivationCode(null);
        medCenterKz.setActivationCode(null);
        medCenterRu.setActivationCode(null);

        medCenterRepository.saveAndFlush(medCenter);
        medCenterRuRepository.saveAndFlush(medCenterRu);
        medCenterKzRepository.saveAndFlush(medCenterKz);

        return true;
    }


    @Override
    public void updatePasswordMedCenter(String email, String password) {
        MedCenter medCenter = medCenterRepository.findByMedCenterEmail(email);
        MedCenterRu medCenterRu = medCenterRuRepository.findByMedCenterEmail(email);
        MedCenterKz medCenterKz = medCenterKzRepository.findByMedCenterEmail(email);

        if(medCenter != null && medCenter != null && medCenter != null){
            System.out.println("ERROR");
        }

        medCenter.setPassword(passwordEncoder.encode(password));
        medCenterRu.setPassword(passwordEncoder.encode(password));
        medCenterKz.setPassword(passwordEncoder.encode(password));

        medCenterRepository.saveAndFlush(medCenter);
        medCenterKzRepository.saveAndFlush(medCenterKz);
        medCenterRuRepository.saveAndFlush(medCenterRu);
    }

    @Override
    public String forgetPasswordMedCenter(String email) {

        String message = "";

        MedCenter medCenter = medCenterRepository.findByMedCenterEmail(email);
        MedCenterKz medCenterKz = medCenterKzRepository.findByMedCenterEmail(email);
        MedCenterRu medCenterRu = medCenterRuRepository.findByMedCenterEmail(email);

        if(medCenter != null && medCenter != null && medCenter != null){
            System.out.println("ERROR");
            return "ERROR with change password";
        }

        medCenter.setActivationCode("forget password");
        medCenterKz.setActivationCode("forget password");
        medCenterRu.setActivationCode("forget password");

        if(!StringUtils.isEmpty(medCenter.getMedCenterEmail())){
            message = String.format(
                    "Hello, %s! \n" + "Welcome to QazMed. Please visit next link: http://localhost:8762/auth-service/registration/medCenter/activate/%s",
                    medCenter.getUsername(),
                    medCenter.getActivationCode()
            );
            mailDelivery.send(medCenter.getMedCenterEmail(), "Activation code", message);
        }

        medCenterRepository.saveAndFlush(medCenter);
        medCenterKzRepository.saveAndFlush(medCenterKz);
        medCenterRuRepository.saveAndFlush(medCenterRu);

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
