package kz.spring.authentication.repository;

import kz.spring.authentication.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Specialty getSpecialtyBySpecialtyId(Long id);
    Specialty getSpecialtyBySpecialtyName(String specialtyName);
}
