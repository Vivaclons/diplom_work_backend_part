package kz.spring.reviewservice.controller;

import kz.spring.reviewservice.model.ReviewDoctor;
import kz.spring.reviewservice.service.impl.IReviewDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review/doctor")
@CrossOrigin(origins = "*")
public class ReviewDoctorController {

    @Autowired
    private IReviewDoctorService iReviewDoctorService;

    @GetMapping("/private/all/{doctorId}")
    public List<ReviewDoctor> getAllDoctorReview(@PathVariable("doctorId") Long doctorId) {
        return iReviewDoctorService.getAllDoctorReview(doctorId);
    }

    @PostMapping("/private/create-review/{doctorId}/{email}")
    public void createReviewDoctor(@RequestBody ReviewDoctor reviewDoctor, @PathVariable("doctorId") Long doctorId, @PathVariable("email") String email){
        iReviewDoctorService.addReviewDoctor(reviewDoctor, doctorId, email);
    }


    @PutMapping("/private/update-review")
    public void updateReviewDoctor(@RequestBody ReviewDoctor reviewDoctor) {
        iReviewDoctorService.updateReviewDoctor(reviewDoctor);
    }

    @DeleteMapping("/private/remove-review/{reviewId}")
    public void removeReviewDoctor(@PathVariable("reviewId") Long reviewId) {
        iReviewDoctorService.removeReviewDoctorById(reviewId);
    }
}
