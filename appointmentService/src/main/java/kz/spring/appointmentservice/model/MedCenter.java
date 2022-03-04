package kz.spring.appointmentservice.model;

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
@Table(name="med_center")
public class MedCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medcenter_id")
    private Long medCenterId;
    private String medCenterName;
    private String medCenterAddress;
    private String medCenterNumber;
    private String medCenterEmail;
    private String medCenterTelNumber;
    private String password;
    private boolean status;
    private String activationCode;
    private String username;

}