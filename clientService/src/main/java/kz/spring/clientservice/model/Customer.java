package kz.spring.clientservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


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
    private String email;
    private String activationCode;
    private String username;
    private String password;
    @Transient
    private String password2;
    private String address;
    private boolean status;
    private Double rating;
    private Integer peopleCount;

    @ManyToMany
    List<Doctor> doctors;
}
