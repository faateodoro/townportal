package com.fteodoro.townportal.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
public class User {
    @Id
    @GeneratedValue
    protected Long id;
    protected LocalDateTime createdAt = now();

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    private String name;

    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Deprecated
    protected User() {}

    public User(UserForm userForm) {
        this.nickname = userForm.nickname();
        this.name = userForm.name();
        this.email = userForm.email();
        this.role = Role.valueOf(userForm.role());
    }

    public User(String nickname, String email, String name, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public Role getRole() {
        return role;
    }

    public void inactivate() {
        this.active = false;
    }

    public void updateData(UserForm userForm) {
        if (userForm.nickname() != null) this.nickname = userForm.nickname();
        if (userForm.name() != null) this.name = userForm.name();
        if (userForm.email() != null) this.email = userForm.email();
        if (userForm.role() != null) this.role = Role.valueOf(userForm.role());
    }
}
