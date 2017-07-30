package com.phonebook.service;

import com.phonebook.model.User;
import com.phonebook.model.dto.UserCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserCreationDto dto) {
        return new User(dto.getPhoneNumber(), dto.getName(), passwordEncoder.encode(dto.getPassword()));
    }
}
