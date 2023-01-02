package com.fteodoro.townportal.base;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
public class BaseEntity {
    @Id
    @GeneratedValue
    protected Long id;
    protected LocalDateTime createdAt = now();

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
