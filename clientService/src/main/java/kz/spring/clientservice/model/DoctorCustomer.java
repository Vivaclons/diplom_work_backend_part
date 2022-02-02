package kz.spring.clientservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DoctorCustomer implements Serializable {
    @Column(name = "doctorId")
    Long doctorId;

    @Column(name = "customerId")
    Long customerId;
}
