//package kz.spring.analysisservice.model;
//
//import kz.spring.clientservice.model.Customer;
//import kz.spring.medservice.model.Doctor;
//import kz.spring.medservice.model.MedCenter;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//public class CustomerAnalysis {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long customerAnalysisId;
//    private Date date;
//
//    @ManyToOne
//    @JoinColumn(name="doctor_id")
//    private Doctor doctor;
//
//    @ManyToOne
//    @JoinColumn(name = "analysis_id")
//    private Analysis analysis;
//
//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    private Customer customer;
//
//    @ManyToOne
//    @JoinColumn(name="medc_enter_id")
//    private MedCenter medCenter;
//}
//
