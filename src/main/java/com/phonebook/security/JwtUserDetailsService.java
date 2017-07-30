package com.phonebook.security;

import com.phonebook.model.User;
import com.phonebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(phoneNumber);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with phone number '%s'.", phoneNumber));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
