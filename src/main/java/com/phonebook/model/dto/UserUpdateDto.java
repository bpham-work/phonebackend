package com.phonebook.model.dto;

import com.phonebook.model.Contact;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserUpdateDto {
    private String name;
    private List<Contact> contacts = new ArrayList<>();
}
