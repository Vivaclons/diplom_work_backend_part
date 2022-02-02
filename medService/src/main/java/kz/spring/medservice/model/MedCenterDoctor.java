package kz.spring.medservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MedCenterDoctor implements Serializable {
    @Column(name = "doctorId")
    Long doctorId;

    @Column(name = "medCenterId")
    Long medCenterId;
}
