package com.fteodoro.townportal.api;

import com.fteodoro.townportal.user.User;
import com.fteodoro.townportal.user.UserDto;
import com.fteodoro.townportal.user.UserForm;
import com.fteodoro.townportal.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public ResponseEntity create(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriComponentsBuilder) {
        User user = new User(userForm);
        userRepository.save(user);
        URI uri = uriComponentsBuilder.path("/usuarios/{id}/detalhe").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Page<UserDto>> getAll(Pageable pageable) {
        Page<UserDto> usersPage = userRepository.findAllByActiveTrue(pageable).map(UserDto::new);
        return ResponseEntity.ok(usersPage);
    }

    @GetMapping("/usuarios/{id}/detalhe")
    public ResponseEntity getOne(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        return ResponseEntity.ok(new UserDto(user));
    }

    @DeleteMapping("/usuarios/{id}")
    @Transactional
    public ResponseEntity exclude(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        user.inactivate();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/usuarios/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody UserForm userForm) {
        User user = userRepository.getReferenceById(id);
        user.updateData(userForm);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
