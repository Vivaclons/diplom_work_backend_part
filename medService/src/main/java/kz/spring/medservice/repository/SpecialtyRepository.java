package kz.spring.medservice.repository;

import kz.spring.medservice.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Specialty getBySpecialtyId(Long id);
    Specialty getById(Long id);
    Specialty getSpecialtyName(String specialtyName);
    List<Specialty> getSpecialtyBy();
}
