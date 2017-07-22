package com.phonebook.repository;

import com.phonebook.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByPhoneNumber(String phoneNumber);
}
