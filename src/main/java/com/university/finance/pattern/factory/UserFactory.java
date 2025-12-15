package com.university.finance.pattern.factory;

import com.university.finance.model.User;

import java.util.UUID;

public class UserFactory {

    public static User createUser(String name, String email, String phone) {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Nom invalide");

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phone);

        return user;
    }
}
