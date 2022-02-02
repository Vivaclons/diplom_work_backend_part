package kz.spring.medservice.model;

import kz.spring.appointmentservice.model.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MedCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medCenterId;
    private String medCenterName;
    private String medCenterAddress;
    private String medCenterNumber;
    private char medCenterEmail;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "medCenterDoctor",
            joinColumns = {
                    @JoinColumn(name = "doctorId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "medCenterId")
            }
    )
    Set<Doctor> medCenterDoctors;

    @OneToMany(mappedBy="medCenter")
    private Set<Appointment> appointments;
}
