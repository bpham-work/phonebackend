package com.phonebook.model.security;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
    private String phoneNumber;
    private String password;
}
