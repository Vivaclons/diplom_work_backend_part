package kz.spring.medservice.service;

import kz.spring.medservice.model.Specialty;
import kz.spring.medservice.repository.SpecialtyRepository;
import kz.spring.medservice.service.impl.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService implements ISpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Specialty getById(Long id) {
        return specialtyRepository.getById(id);
    }

    @Override
    public Specialty getBySpecialtyName(String specialtyName) {
        return specialtyRepository.getSpecialtyBySpecialtyName(specialtyName);
    }

    @Override
    public void update(Specialty specialty) {
        specialtyRepository.saveAndFlush(specialty);
    }

    @Override
    public List<Specialty> getAllSpecialty() {
        return specialtyRepository.findAll();
    }

    @Override
    public void DeleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
