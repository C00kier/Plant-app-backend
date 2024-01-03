package com.plantapp.plantapp.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {
    ADMIN,
    USER,
    GOOGLE_USER
}
