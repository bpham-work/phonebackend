package com.phonebook.service;

import com.phonebook.exception.UserCreationException;
import com.phonebook.exception.UserNotFoundException;
import com.phonebook.model.User;
import com.phonebook.model.dto.UserCreationDto;
import com.phonebook.model.dto.UserUpdateDto;
import com.phonebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepo;
    private UserFactory userFactory;

    @Autowired
    public UserService(UserRepository userRepo, UserFactory userFactory) {
        this.userRepo = userRepo;
        this.userFactory = userFactory;
    }

    public User create(UserCreationDto dto) {
        User doesUserExist = userRepo.findByPhoneNumber(dto.getPhoneNumber());
        if (doesUserExist != null) {
            throw new UserCreationException("User already exists with this phone number");
        }
        User user = userFactory.create(dto);
        return save(user);
    }

    public User update(String id, UserUpdateDto updateData) {
        User user = findById(id);
        user = user.updateWith(updateData);
        return save(user);
    }

    public User findByPhoneNumber(String phoneNumber) {
        User user = userRepo.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new UserNotFoundException("User not found with phone number: " + phoneNumber);
        }

        return user;
    }

    public User findById(String id) {
        User user = userRepo.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return user;
    }

    public User save(User user) {
        user.validate();
        return userRepo.save(user);
    }

    public Iterable<User> get() {
        return userRepo.findAll();
    }
}
