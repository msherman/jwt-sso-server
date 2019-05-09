package com.msdev.trackme.web;

import com.msdev.trackme.model.User;
import com.msdev.trackme.repository.MyUserRepository;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private final MyUserRepository myUserRepository;

    public UserController(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @GetMapping("users/{userId}")
    @PreAuthorize("#oauth2.hasScope('users.read')")
    public User findUserById(@PathVariable("userId") String userId) {
        return myUserRepository.findById(userId).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping("/users")
    @PreAuthorize("#oauth2.hasScope('users.read')")
    public List<User> findUserByIdIN(@RequestBody List<String> userIds) {
        return myUserRepository.findByIdIn(userIds).orElseThrow(() -> new NotFoundException());
    }
}
