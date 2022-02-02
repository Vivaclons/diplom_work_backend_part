package kz.spring.medservice.service;

import kz.spring.medservice.model.MedCenter;
import kz.spring.medservice.repository.MedCenterRepository;
import kz.spring.medservice.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedCenterService implements IMedCenterService {

    @Autowired
    private MedCenterRepository medCenterRepository;

    @Override
    public MedCenter getById(Long id) {
        return medCenterRepository.getById(id);
    }

    @Override
    public MedCenter getByMedCenterName(String medCenterName) {
        return medCenterRepository.getMedCenterName(medCenterName);
    }

    @Override
    public MedCenter getByMedCenterAddress(String address){
        return medCenterRepository.getMedCenterAddress(address);
    }

    @Override
    public void update(MedCenter medCenter) {
        medCenterRepository.saveAndFlush(medCenter);
    }

    @Override
    public List<MedCenter> getAllMedCenterBy() {
        return medCenterRepository.getMedCenterBy();
    }

    @Override
    public void DeleteById(Long id) {
        medCenterRepository.deleteById(id);
    }
}
