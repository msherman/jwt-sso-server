package com.msdev.trackme.security;

import com.msdev.trackme.model.Persona;
import com.msdev.trackme.model.User;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails, CredentialsContainer {
    private static final long serialVersionUID = 1L;

    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public void eraseCredentials() {
        user.setPassword(null);
    }

    @Override
    public boolean isEnabled() {
        return true;
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
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(user.getPersonas())) {
            return null;
        }

        return user.getPersonas().stream()
                .flatMap(persona -> persona.getRoles().stream()
                            .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toString()))
                        )
                .collect(Collectors.toSet());
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Collection<Persona> getPersonas() {
        return user.getPersonas();
    }

    public String getId() {
        return user.getId();
    }

}
