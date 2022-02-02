package kz.spring.medservice.model;

import javax.persistence.*;

import kz.spring.appointmentservice.model.Appointment;
import kz.spring.clientservice.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorName;
    private String doctorSurname;
    private String doctorTelNumber;
    private char doctorEmail;
    private String doctorPassword;

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
    Set<MedCenter> medCenterDoctor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctorCustomer",
            joinColumns = {
                    @JoinColumn(name = "doctorId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "customerId")
            }
    )
    Set<Customer> doctorCustomer;

    @ManyToOne
    @JoinColumn(name="specialtyId", nullable=false)
    private Specialty specialty;

    @OneToMany(mappedBy="doctor")
    private Set<Appointment> appointments;
}
