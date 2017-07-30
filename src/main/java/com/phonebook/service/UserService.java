package com.phonebook.service;

import com.phonebook.exception.UserCreationException;
import com.phonebook.model.User;
import com.phonebook.model.dto.UserCreationDto;
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

    public User update(String id, User updateData) {
        User user = userRepo.findOne(id);
        user = user.updateWith(updateData);
        return save(user);
    }

    public User findByPhoneNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber);
    }

    public User save(User user) {
        user.validate();
        return userRepo.save(user);
    }

    public Iterable<User> get() {
        return userRepo.findAll();
    }
}
