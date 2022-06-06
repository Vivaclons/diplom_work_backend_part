package kz.spring.reviewservice.service.impl;

import kz.spring.reviewservice.model.ReviewDoctor;
import kz.spring.reviewservice.model.ReviewMedCenter;

import java.util.List;

public interface IReviewMedCenterService {
    void createReviewMedCenter(ReviewMedCenter reviewMedCenter, Long medCenter_id, String email);

    void updateReviewMedCenter(ReviewMedCenter reviewMedCenter);

    void removeReviewMedCenter(Long reviewId);

    List<ReviewMedCenter> getAllMedCenterReview(Long medCenter_id);
}
