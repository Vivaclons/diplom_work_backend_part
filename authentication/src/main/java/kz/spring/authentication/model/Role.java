package kz.spring.authentication.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public enum Role implements GrantedAuthority{
    USER("USER"),
    ADMIN("ADMIN"),
    DOCTOR("DOCTOR"),
    MED_CENTER("MED_CENTER");

    public final String name;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//
//    @Override
//    public String getAuthority() {
//        return name;
//    }
    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
