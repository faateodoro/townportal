package com.fteodoro.townportal.user;

public record UserDto(String nickname, String email, String name) {
    public UserDto(User user) {
        this(user.getNickname(), user.getEmail(), user.getName());
    }
}
