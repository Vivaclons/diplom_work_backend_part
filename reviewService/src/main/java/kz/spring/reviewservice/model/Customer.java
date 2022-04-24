package kz.spring.reviewservice.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="customer")
public class Customer{
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
