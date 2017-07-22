package com.phonebook.model;

import com.phonebook.exception.InvalidContactException;
import lombok.Data;

@Data
public class Contact {
    private String name;
    private String phoneNumber;

    public void validate() {
        boolean hasName = name != null;
        boolean hasPhoneNumber = phoneNumber != null;
        if (!hasName || !hasPhoneNumber) {
            throw new InvalidContactException("Contact must have name and phone number");
        }
    }
}
