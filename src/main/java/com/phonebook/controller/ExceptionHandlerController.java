package com.phonebook.controller;

import com.phonebook.exception.InvalidContactException;
import com.phonebook.exception.InvalidUserException;
import com.phonebook.exception.UserCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionHandlerController {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity invalidUser() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidContactException.class)
    public ResponseEntity invalidContact() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity userCreationException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
