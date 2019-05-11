package com.msdev.trackme.web;

import com.msdev.trackme.model.User;
import com.msdev.trackme.repository.MyUserRepository;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    private final MyUserRepository myUserRepository;

    public UserController(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @GetMapping("/users/{userId}")
    public User findUserById(@PathVariable("userId") String userId) {
        return myUserRepository.findById(userId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/users")
    public List<User> findUserByIdIN(@RequestBody List<String> userIds) {
        return myUserRepository.findByIdIn(userIds).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/username/{userNameOrEmail}")
    public User findUserByName(@PathVariable String userNameOrEmail) {
        return myUserRepository.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail).orElseThrow(NotFoundException::new);
    }
}
