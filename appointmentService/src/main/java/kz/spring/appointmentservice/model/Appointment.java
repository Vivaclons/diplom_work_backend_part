package kz.spring.appointmentservice.model;

import kz.spring.clientservice.model.Customer;
import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.model.MedCenter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private Date date;
}
