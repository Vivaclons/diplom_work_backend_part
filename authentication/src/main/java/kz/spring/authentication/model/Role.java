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
    USER, ADMIN;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//
//    @Override
//    public String getAuthority() {
//        return name;
//    }

    @Override
    public String getAuthority() {
        return name();
    }
}
