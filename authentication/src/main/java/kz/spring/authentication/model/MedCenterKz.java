package kz.spring.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedCenterKz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medcenter_id")
    private Long medCenterId;
    private String medCenterName;
    private String medCenterAddress;
    private String medCenterNumber;
    private String medCenterEmail;
    private String medCenterTelNumber;
    private String workTimeFrom;
    private String workTimeTo;
    private String password;
    private boolean status;
    private String activationCode;
    private String username;
    private String latitude;
    private String longitude;
    private Double distance;
    private Double rating;
    private Integer peopleCount;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "medCenter_role", joinColumns = @JoinColumn(name = "medCenter_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
