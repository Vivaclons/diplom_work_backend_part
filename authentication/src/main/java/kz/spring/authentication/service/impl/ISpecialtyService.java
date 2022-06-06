package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.Specialty;

import java.util.List;

public interface ISpecialtyService {
    Specialty getById(Long id);
    Specialty getBySpecialtyName(String specialtyName);
    void update(Specialty specialty);
    List<Specialty> getAllSpecialty();
    void DeleteById(Long id);
}
