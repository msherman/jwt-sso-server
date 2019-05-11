package com.msdev.trackme.dataloader;

import com.google.common.collect.ImmutableSet;
import com.msdev.trackme.model.Persona;
import com.msdev.trackme.model.User;
import com.msdev.trackme.repository.MyUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoadUsers implements ApplicationRunner {

    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    public LoadUsers(MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        myUserRepository.deleteAll();

        myUserRepository.save(buildUser("admin", "user", Personas.ADMIN));
        myUserRepository.save(buildUser("free", "user", Personas.FREE_USER));
        myUserRepository.save(buildUser("paid", "user", Personas.PAID_USER));
        myUserRepository.save(buildUser("moderator", "user", Personas.MODERATOR));
    }

    private User buildUser(String first, String last, Persona persona) {
        return new User(first,
                last,
                first,
                String.format("%s@trackme.fake", first),
                passwordEncoder.encode("password"),
                ImmutableSet.of(persona));
    }
}
