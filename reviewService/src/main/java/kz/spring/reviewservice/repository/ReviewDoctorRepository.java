package kz.spring.reviewservice.repository;

import kz.spring.reviewservice.model.ReviewDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDoctorRepository extends JpaRepository<ReviewDoctor, Long> {
}
