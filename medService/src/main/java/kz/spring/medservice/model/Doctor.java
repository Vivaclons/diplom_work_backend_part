package kz.spring.medservice.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;
    private String doctorName;
    private String doctorSurname;
    private String doctorTelNumber;
    private char doctorEmail;
    private String doctorPassword;

    @ManyToOne
    @JoinColumn(name="specialtyId")
    private Specialty specialty;

    @ManyToMany
    @JoinTable(
            name = "med_center_doctor",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "medcenter_id")}
    )
    private Set<Doctor> medcenterDoctor = new HashSet<>();

//    @OneToMany(mappedBy="doctor")
//    private Set<Appointment> appointments;

//    @OneToMany(mappedBy="doctor")
//    private Set<MedCenterDoctor> medCenterDoctors;
//
//    @OneToMany(mappedBy = "doctor")
//    private Set<DoctorCustomer> doctorCustomers;
//
//    @OneToMany(mappedBy="doctor")
//    private Set<CustomerAnalysis> analyses;
}
