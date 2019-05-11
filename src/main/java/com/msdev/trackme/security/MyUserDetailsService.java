package com.msdev.trackme.security;

import com.msdev.trackme.model.User;
import com.msdev.trackme.repository.MyUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private MyUserRepository myUserRepository;

    public MyUserDetailsService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = myUserRepository.findByUserName(username);

        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find user %s", username)));
}

    @Transactional
    public UserDetails loadUserById(String id) {
        Optional<User> user = myUserRepository.findById(id);

        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find user with id %s", id)));
    }
}
