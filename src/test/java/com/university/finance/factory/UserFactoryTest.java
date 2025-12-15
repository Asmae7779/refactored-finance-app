package com.university.finance.factory;

import com.university.finance.model.User;
import com.university.finance.pattern.factory.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void shouldCreateUserWithValidData() {
        User user = UserFactory.createUser("Ali", "ali@mail.com", "0600000000");

        assertNotNull(user);
        assertEquals("Ali", user.getName());
        assertEquals("ali@mail.com", user.getEmail());
        assertEquals("0600000000", user.getPhoneNumber());
    }

    @Test
    void shouldGenerateUserId() {
        User user = UserFactory.createUser("Test", "t@mail.com", "0611111111");
        assertNotNull(user.getId());
    }
}
