package ru.yaro.crudapp.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordEncoder {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
