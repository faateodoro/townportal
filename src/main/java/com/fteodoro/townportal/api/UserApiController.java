package com.fteodoro.townportal.api;

import com.fteodoro.townportal.user.*;
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
public class UserApiController {
    private UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/usuarios")
    @Transactional
    public ResponseEntity create(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriComponentsBuilder) {
        User user = this.userService.saveUser(userForm);
        URI uri = this.getUriFor(uriComponentsBuilder, user);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Page<UserDto>> getAll(Pageable pageable) {
        Page<UserDto> usersPage = this.userService.pageAllUsers(pageable);
        return ResponseEntity.ok(usersPage);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(new UserDto(user));
    }

    @DeleteMapping("/usuarios/{id}")
    @Transactional
    public ResponseEntity exclude(@PathVariable Long id) {
        userService.inactivateUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/usuarios/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody UserForm userForm) {
        User user = userService.updateUser(id, userForm);
        return ResponseEntity.ok(user);
    }

    private URI getUriFor(UriComponentsBuilder uriComponentsBuilder, User user) {
        return uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(user.getId()).toUri();
    }
}
