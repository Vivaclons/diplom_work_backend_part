package kz.spring.medservice.service;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.model.MedCenter;
import kz.spring.medservice.model.Specialty;
import kz.spring.medservice.repository.DoctorRepository;
import kz.spring.medservice.repository.MedCenterRepository;
import kz.spring.medservice.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class MedCenterService implements IMedCenterService {

    @Autowired
    private MedCenterRepository medCenterRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<MedCenter> searchMedCenterByMedCenterName(String medCenterName) {
        return medCenterRepository.getMedCenterByMedCenterNameIsLike("%" + medCenterName + "%");
    }

    @Override
    public List<MedCenter> searchMedCenterByMedCenterAddress(String medCenterAddress) {
        return medCenterRepository.getMedCenterByMedCenterAddressIsLike("%" + medCenterAddress + "%");
    }

    @Override
    public MedCenter removeDoctor(Long medCenterId, Long doctorId) {

        MedCenter medCenter = medCenterRepository.getByMedCenterId(medCenterId);

        boolean check = false;

        for(int i = 0; i < medCenter.getDoctors().size(); i++){
            if(medCenter.getDoctors().get(i).getDoctorId().equals(doctorId)){
                medCenter.getDoctors().remove(i);
                check = true;
                break;
            }
        }

        if(check){
            return medCenterRepository.saveAndFlush(medCenter);
        }
        return null;
    }

    @Override
    public MedCenter addDoctor(Long medCenterId, Long doctorId) {
        MedCenter medCenter = medCenterRepository.getByMedCenterId(medCenterId);

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        boolean check = false;

        if(doctor != null && medCenter != null){
            medCenter.getDoctors().add(doctor);
            if(!check){
                return medCenterRepository.saveAndFlush(medCenter);
            }
        }
        return null;
    }

    @Override
    public MedCenter updateDoctor(Long medCenterId, Long doctorId) {
        MedCenter medCenter = medCenterRepository.getByMedCenterId(medCenterId);

        if(medCenter != null && medCenter.getMedCenterId() != null && medCenter.getMedCenterId() != 0L) {
            for (int i = 0; i < medCenter.getDoctors().size(); i++) {
                medCenter.getDoctors().get(i).setDoctorId(doctorId);
                return medCenterRepository.saveAndFlush(medCenter);
            }
        }
        return null;
    }

    @Override
    public MedCenter getById(Long id) {
        return medCenterRepository.getByMedCenterId(id);
    }

    @Override
    public MedCenter getByMedCenterName(String medCenterName) {
        return medCenterRepository.getMedCenterByMedCenterName(medCenterName);
    }

    @Override
    public MedCenter getByMedCenterAddress(String address){
        return medCenterRepository.getMedCenterByMedCenterAddress(address);
    }

    @Override
    public void update(MedCenter medCenter) {
        medCenterRepository.saveAndFlush(medCenter);
    }

    @Override
    public List<MedCenter> getAllMedCenter() {
        return medCenterRepository.findAll();
    }

    @Override
    public void DeleteById(Long id) {
        medCenterRepository.deleteById(id);
    }
}
