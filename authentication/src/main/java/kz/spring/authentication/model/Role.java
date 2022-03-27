package kz.spring.authentication.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    USER("USER"),
    ADMIN("ADMIN"),
    DOCTOR("DOCTOR"),
    MED_CENTER("MED_CENTER");

    public final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
