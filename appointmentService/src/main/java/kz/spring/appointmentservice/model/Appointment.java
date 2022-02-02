package kz.spring.appointmentservice.model;

import kz.spring.clientservice.model.Customer;
import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.model.MedCenter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @Column(name = "doctorId")
    Long doctorId;

    @Column(name = "customerId")
    Long customerId;

    @Column(name = "medCenterId")
    Long medCenterId;

    private Date date;

    @ManyToOne
    @JoinColumn(name="doctorId", nullable=false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="customerId", nullable=false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="medCenterId", nullable=false)
    private MedCenter medCenter;
}
