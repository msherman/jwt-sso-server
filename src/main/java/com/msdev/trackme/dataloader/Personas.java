package com.msdev.trackme.dataloader;

import com.msdev.trackme.model.Persona;
import com.msdev.trackme.model.constants.PersonaName;
import com.msdev.trackme.model.constants.RoleName;

public class Personas {
    public static final Persona ADMIN = Persona.of(PersonaName.ADMIN)
            .withRole(RoleName.ADMIN)
            .withRole(RoleName.MODERATOR)
            .withRole(RoleName.FREE)
            .withRole(RoleName.USER)
            .withRole(RoleName.PAID);

    public static final Persona FREE_USER = Persona.of(PersonaName.USER)
            .withRole(RoleName.USER)
            .withRole(RoleName.FREE);

    public static final Persona PAID_USER = Persona.of(PersonaName.USER)
            .withRole(RoleName.USER)
            .withRole(RoleName.FREE)
            .withRole(RoleName.PAID);

    public static final Persona MODERATOR = Persona.of(PersonaName.MODERATOR)
            .withRole(RoleName.USER)
            .withRole(RoleName.MODERATOR)
            .withRole(RoleName.FREE)
            .withRole(RoleName.PAID);
}
