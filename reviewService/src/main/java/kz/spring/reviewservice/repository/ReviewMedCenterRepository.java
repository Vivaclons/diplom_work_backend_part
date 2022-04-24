package kz.spring.reviewservice.repository;

import kz.spring.reviewservice.model.ReviewMedCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewMedCenterRepository extends JpaRepository<ReviewMedCenter, Long> {
}
