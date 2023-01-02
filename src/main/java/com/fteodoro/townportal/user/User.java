package com.fteodoro.townportal.user;

import com.fteodoro.townportal.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User extends BaseEntity {

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
        this.email = userForm.email();
        this.role = Role.valueOf(userForm.role());
    }

    public User(String nickname, String email, String name, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.role = role;
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
}
