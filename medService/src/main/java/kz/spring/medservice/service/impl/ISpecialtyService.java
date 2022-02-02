package kz.spring.medservice.service.impl;

import kz.spring.medservice.model.Specialty;

import java.util.List;

public interface ISpecialtyService {
    Specialty getById(Long id);
    Specialty getBySpecialtyName(String specialtyName);
    void update(Specialty specialty);
    List<Specialty> getAllSpecialtyBy();
    void DeleteById(Long id);
}
