package com.fteodoro.townportal.api;

import com.fteodoro.townportal.user.User;
import com.fteodoro.townportal.user.UserForm;
import com.fteodoro.townportal.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/usuarios")
    public ResponseEntity create(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriComponentsBuilder) {
        User user = new User(userForm);
        userRepository.save(user);
        URI uri = uriComponentsBuilder.path("/usuarios/{id}/detalhe").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
