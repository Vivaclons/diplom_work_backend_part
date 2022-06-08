package kz.spring.reviewservice.service;

import kz.spring.reviewservice.model.Customer;
import kz.spring.reviewservice.model.Doctor;
import kz.spring.reviewservice.model.ReviewDoctor;
import kz.spring.reviewservice.repository.CustomerRepository;
import kz.spring.reviewservice.repository.DoctorRepository;
import kz.spring.reviewservice.repository.ReviewDoctorRepository;
import kz.spring.reviewservice.service.impl.IReviewDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewDoctorService implements IReviewDoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReviewDoctorRepository reviewDoctorRepository;

    @Override
    public List<ReviewDoctor> getAllDoctorReview(Long doctorId) {

        List<ReviewDoctor> reviewDoctors = reviewDoctorRepository.findAll();

        List<ReviewDoctor> reviewDoctors1 = new ArrayList<>();

        for(int i = 0; i < reviewDoctors.size(); i++){
            if(reviewDoctors.get(i).getDoctor().getDoctorId() == doctorId){
                reviewDoctors1.add(reviewDoctors.get(i));
            }
        }

        return reviewDoctors1;
    }

    @Override
    public void addReviewDoctor(ReviewDoctor reviewDoctor, Long doctor_id, String email) {
        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctor_id);

        Customer customer = customerRepository.findCustomerByEmail(email);

        if(doctor != null && customer != null){

            if(doctor.getPeopleCount() < 75 && reviewDoctor.getReviewRating() < 4){
                reviewDoctor.setReviewRating(reviewDoctor.getReviewRating() + 1);
            }

            doctor.setPeopleCount(doctor.getPeopleCount() + 1);
            doctor.setRating((doctor.getRating() * doctor.getPeopleCount() + reviewDoctor.getReviewRating())/(doctor.getPeopleCount() + 1));

            reviewDoctor.setDoctor(doctor);
            reviewDoctor.setCustomer(customer);
            reviewDoctorRepository.saveAndFlush(reviewDoctor);
        } else{
            System.out.println("Empty user or doctor");
        }
    }

    @Override
    public void updateReviewDoctor(ReviewDoctor reviewDoctor) {
        reviewDoctorRepository.saveAndFlush(reviewDoctor);
    }

    @Override
    public void removeReviewDoctorById(Long reviewId) {
        reviewDoctorRepository.deleteById(reviewId);
    }
}
