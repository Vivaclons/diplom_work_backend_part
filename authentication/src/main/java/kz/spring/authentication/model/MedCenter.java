package kz.spring.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="med_center")
public class MedCenter implements UserDetails {
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
    private String about;
    private Integer fees;
    private String avatar;

    @ManyToMany
    List<Doctor> doctors;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "medCenter_role", joinColumns = @JoinColumn(name = "medCenter_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
