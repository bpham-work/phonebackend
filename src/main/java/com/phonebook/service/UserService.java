package com.phonebook.service;

import com.phonebook.exception.UserCreationException;
import com.phonebook.model.User;
import com.phonebook.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User create(User user) {
        User doesUserExist = userRepo.findByPhoneNumber(user.getPhoneNumber());
        if (doesUserExist != null) {
            throw new UserCreationException("User already exists with this phone number");
        }
        user.setFirstTimeLogin();
        return save(user);
    }

    public User update(String id, User updateData) {
        User user = userRepo.findOne(id);
        user = user.updateWith(updateData);
        return save(user);
    }

    public User save(User user) {
        user.validate();
        return userRepo.save(user);
    }

    public Iterable<User> get() {
        return userRepo.findAll();
    }
}
