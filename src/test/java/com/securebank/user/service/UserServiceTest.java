package com.securebank.user.service;

import com.securebank.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testCreateUser() {
        User user = new User(null, "Ram", "ram@gmail.com", "Admin");
        User saved = service.createUser(user);
        assertNotNull(saved.getId());
    }
}
