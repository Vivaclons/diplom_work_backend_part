package kz.spring.analysisservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String address;
    private boolean status;
    private Double rating;
    private Integer peopleCount;

}
