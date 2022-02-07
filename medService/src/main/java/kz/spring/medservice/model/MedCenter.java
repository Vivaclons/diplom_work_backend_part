package kz.spring.medservice.model;

//import kz.spring.analysisservice.model.CustomerAnalysis;
//import kz.spring.appointmentservice.model.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MedCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medcenter_id")
    private Long medCenterId;
    private String medCenterName;
    private String medCenterAddress;
    private String medCenterNumber;
    private char medCenterEmail;

//    @ManyToMany
//    @JoinTable(
//            name = "appointment",
//            joinColumns = {@JoinColumn(name = "doctor_id")},
//            inverseJoinColumns = {@JoinColumn(name = "medcenter_id")}
//    )
//    private Set<MedCenter> medcenterAppointment = new HashSet<>();

//    @OneToMany(mappedBy = "medCenter")
//    private Set<MedCenterDoctor> medCenterDoctors;

//    @OneToMany(mappedBy="medCenter")
//    private Set<Appointment> appointments;
//
//    @OneToMany(mappedBy="medCenter")
//    private Set<CustomerAnalysis> analyses;
}
