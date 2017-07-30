package com.phonebook.security;

import com.phonebook.model.User;
import com.phonebook.model.security.JwtUser;

import java.util.ArrayList;

public class JwtUserFactory {
    public static JwtUser create(User user) {
        return new JwtUser(user.getPhoneNumber(), user.getPassword(), new ArrayList<>(), true);
    }
}
