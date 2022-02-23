package kz.spring.authentication.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class Customer implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerTelNumber;
    private String customerEmail;
    private String Password;
    private String Address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private List<Role> roles;

    private boolean isAdmin;
    private boolean isDoctor;
    private boolean isMedCenter;

    public Customer(Long customerId, String customerName, String customerSurname, String customerTelNumber, String customerEmail, String password, String address, List<Role> roles) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerTelNumber = customerTelNumber;
        this.customerEmail = customerEmail;
        Password = password;
        Address = address;
        this.roles = roles;

        isAdmin = false;
        isDoctor = false;
        isMedCenter = false;

        for (Role i : roles ) {
            if (i.getName().equals( "ADMIN" )) {
                isAdmin = true;
            } else if(i.getName().equals("DOCTOR")){
                isDoctor = true;
            } else if(i.getName().equals("MEDCENTER")){
                isMedCenter = true;
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return null;
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
        return true;
    }
}
