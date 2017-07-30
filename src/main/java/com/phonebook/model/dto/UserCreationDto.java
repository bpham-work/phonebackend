package com.phonebook.model.dto;

import lombok.Data;

@Data
public class UserCreationDto {
    private String phoneNumber;
    private String password;
    private String name;
}
