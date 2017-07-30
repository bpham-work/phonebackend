package com.phonebook.controller;

import com.phonebook.model.User;
import com.phonebook.model.dto.UserCreationDto;
import com.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> getUsers() {
        Iterable<User> users = userService.get();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody UserCreationDto user) {
        User createdUser = userService.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable("id") String id, @RequestBody User user) {
        User savedUser = userService.update(id, user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
}
