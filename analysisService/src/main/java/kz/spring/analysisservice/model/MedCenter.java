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
public class MedCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medcenter_id")
    private Long medCenterId;
    private String medCenterName;
    private String medCenterAddress;
    private String medCenterNumber;
    private char medCenterEmail;
}
