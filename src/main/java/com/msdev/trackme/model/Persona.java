package com.msdev.trackme.model;

import com.msdev.trackme.model.constants.PersonaName;
import com.msdev.trackme.model.constants.RoleName;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Persona {
    private PersonaName name;
    private Set<RoleName> roles = new HashSet<>();

    public Persona() {
    }

    public static Persona of(PersonaName name) {
        return new Persona(name);
    }

    public Persona(PersonaName name, Set<RoleName> roles) {
        this.name = name;
        this.roles = roles;
    }

    public Persona(PersonaName name) {
        this.name = name;
    }

    public Persona withRole(RoleName role) {
        getRoles().add(role);
        return this;
    }

    public Persona withRoles(Collection<RoleName> roles) {
        getRoles().addAll(roles);
        return this;
    }

    public PersonaName getName() {
        return name;
    }

    public void setName(PersonaName name) {
        this.name = name;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }
}
