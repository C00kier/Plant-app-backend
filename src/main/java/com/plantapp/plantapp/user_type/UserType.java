package com.plantapp.plantapp.user_type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {
    ADMIN("administrator"),
    USER("user");

    private final String userTypeName;
}
