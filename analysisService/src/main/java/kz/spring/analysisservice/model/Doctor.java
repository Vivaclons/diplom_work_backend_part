package kz.spring.analysisservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;
    private String doctorName;
    private String doctorSurname;
    private String doctorTelNumber;
    private String doctorEmail;
    private String workTimeFrom;
    private String workTimeTo;
    private String password;
    private boolean status;
    private String activationCode;
    private String username;
    private String address;
    private String latitude;
    private String longitude;
    private Double distance;
    private Double rating;
    private Integer peopleCount;

}
