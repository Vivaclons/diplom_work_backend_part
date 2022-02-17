package kz.spring.clientservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerTelNumber;
    private String customerEmail;
    private String Password;


    //
//    @ManyToMany
//    @JoinTable(
//            name = "doctor_customer",
//            joinColumns = {@JoinColumn(name = "doctor_id")},
//            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
//    )
//    private Set<Customer> customerDoctor = new HashSet<>();

//    @ManyToMany
//    @JoinTable(
//            name = "appointment",
//            joinColumns = {@JoinColumn(name = "doctor_id")},
//            inverseJoinColumns = {@JoinColumn(name = "customer_id"), @JoinColumn(name = "appointment_id"), @JoinColumn(name = "medcenter_id")}
//    )
//    private Set<Customer> customerAppointment = new HashSet<>();

//    @OneToMany(mappedBy = "customer")
//    private Set<DoctorCustomer> doctorCustomers;
//
//    @OneToMany(mappedBy="customer")
//    private Set<Appointment> appointments;
//
//    @OneToMany(mappedBy="customer")
//    private Set<Analysis> analyses;
}
