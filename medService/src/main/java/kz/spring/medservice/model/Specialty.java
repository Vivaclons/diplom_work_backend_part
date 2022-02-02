package kz.spring.medservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialtyId;
    private String specialtyName;

    @OneToMany(mappedBy="specialty")
    private Set<Doctor> doctors;
}
