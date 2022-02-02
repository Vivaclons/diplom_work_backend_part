package kz.spring.clientservice.model;

import kz.spring.appointmentservice.model.Appointment;
import kz.spring.medservice.model.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerTelNumber;
    private char customerEmail;
    private String Password;

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
    Set<Doctor> doctorCustomer;

    @OneToMany(mappedBy="customer")
    private Set<Appointment> appointments;
}
