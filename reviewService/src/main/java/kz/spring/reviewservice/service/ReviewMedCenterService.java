package kz.spring.reviewservice.service;

import kz.spring.reviewservice.model.*;
import kz.spring.reviewservice.repository.CustomerRepository;
import kz.spring.reviewservice.repository.MedCenterRepository;
import kz.spring.reviewservice.repository.ReviewDoctorRepository;
import kz.spring.reviewservice.repository.ReviewMedCenterRepository;
import kz.spring.reviewservice.service.impl.IReviewMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewMedCenterService implements IReviewMedCenterService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReviewMedCenterRepository reviewMedCenterRepository;

    @Autowired
    private MedCenterRepository medCenterRepository;

    @Override
    public void createReviewMedCenter(ReviewMedCenter reviewMedCenter, Long medCenter_id, String email) {
        MedCenter medCenter = medCenterRepository.getById(medCenter_id);

        Customer customer = customerRepository.findCustomerByEmail(email);

        if(medCenter != null && customer != null){

            if(medCenter.getPeopleCount() < 75 && reviewMedCenter.getReviewRating() < 4){
                reviewMedCenter.setReviewRating(reviewMedCenter.getReviewRating() + 1);
            }

            medCenter.setPeopleCount(medCenter.getPeopleCount() + 1);
            medCenter.setRating((medCenter.getRating() * medCenter.getPeopleCount() + reviewMedCenter.getReviewRating())/(medCenter.getPeopleCount() + 1));

            reviewMedCenter.setMedCenter(medCenter);
            reviewMedCenter.setCustomer(customer);
            reviewMedCenterRepository.saveAndFlush(reviewMedCenter);
        } else{
            System.out.println("Empty user or doctor");
        }
    }

    @Override
    public void updateReviewMedCenter(ReviewMedCenter reviewMedCenter) {
        reviewMedCenterRepository.saveAndFlush(reviewMedCenter);
    }

    @Override
    public void removeReviewMedCenter(Long reviewId) {
        reviewMedCenterRepository.deleteById(reviewId);
    }

    @Override
    public List<ReviewMedCenter> getAllMedCenterReview(Long medCenter_id) {
        List<ReviewMedCenter> reviewMedCenters = reviewMedCenterRepository.findAll();

        List<ReviewMedCenter> reviewMedCenters1 = new ArrayList<>();

        for(int i = 0; i < reviewMedCenters.size(); i++){
            if(reviewMedCenters.get(i).getMedCenter().getMedCenterId() == medCenter_id){
                reviewMedCenters1.add(reviewMedCenters.get(i));
            }
        }

        return reviewMedCenters1;
    }
}
