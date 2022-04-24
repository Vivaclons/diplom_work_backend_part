package kz.spring.reviewservice.controller;

import kz.spring.reviewservice.model.ReviewDoctor;
import kz.spring.reviewservice.model.ReviewMedCenter;
import kz.spring.reviewservice.service.impl.IReviewMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review/medCenter/")
public class ReviewMedCenterController {

    @Autowired
    private IReviewMedCenterService iReviewMedCenterService;

    @GetMapping("/private/all/{medCenter_id}")
    public List<ReviewMedCenter> getAllMedCenterReview(@PathVariable("medCenter_id") Long medCenter_id) {
        return iReviewMedCenterService.getAllMedCenterReview(medCenter_id);
    }

    @PostMapping("/private/create-review/{medCenter_id}/{customerId}")
    public void createReviewMedCenter(@RequestBody ReviewMedCenter reviewMedCenter, @PathVariable("medCenter_id") Long medCenter_id, @PathVariable("customerId") Long customerId){
        iReviewMedCenterService.createReviewMedCenter(reviewMedCenter, medCenter_id, customerId);
    }


    @PutMapping("/private/update-review")
    public void updateReviewMedCenter(@RequestBody ReviewMedCenter reviewMedCenter) {
        iReviewMedCenterService.updateReviewMedCenter(reviewMedCenter);
    }

    @DeleteMapping("/private/remove-review/{reviewId")
    public void removeReviewMedCenter(@PathVariable("reviewId") Long reviewId) {
        iReviewMedCenterService.removeReviewMedCenter(reviewId);
    }
}
