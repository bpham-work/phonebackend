package com.phonebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.phonebook.exception.InvalidUserException;
import com.phonebook.model.dto.UserUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String phoneNumber;
    private String name;
    private boolean isFirstTimeLogin;
    private List<Contact> contacts = new ArrayList<>();

    @JsonIgnore
    private String password;

    public User(String phoneNumber, String name, String password) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.password = password;
        this.isFirstTimeLogin = true;
    }

    public void validate() {
        validatePhoneNumber();
        validateContacts();
    }

    public void setFirstTimeLogin() {
        isFirstTimeLogin = true;
    }

    public void setNotFirstTimeLogin() {
        isFirstTimeLogin = false;
    }

    public User updateWith(UserUpdateDto updateData) {
        return new User(id, phoneNumber, updateData.getName(), isFirstTimeLogin, updateData.getContacts(), password);
    }

    private void validateContacts() {
        for (Contact contact : contacts) {
            contact.validate();
        }
    }

    private void validatePhoneNumber() {
        boolean hasPhoneNumber = phoneNumber != null;
        if (!hasPhoneNumber) {
            throw new InvalidUserException("User must have a phone number");
        }
        if (phoneNumber.length() != 10) {
            throw new InvalidUserException("User phone number is invalid");
        }
    }
}
