package com.phonebook.model;

import com.phonebook.exception.InvalidUserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String phoneNumber;
    private String name;
    private boolean isFirstTimeLogin;
    private List<Contact> contacts;

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

    public User updateWith(User user) {
        return new User(id, phoneNumber, user.getName(), isFirstTimeLogin, user.getContacts());
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
