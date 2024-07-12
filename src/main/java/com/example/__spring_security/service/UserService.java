package com.example.__spring_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;


//Save the user details in database using User.builder() instead of entityManager.. and save using userDetailsManager.createUser()

@Service
public class UserService {

    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    //Injecting userDetailsManager in Constructor Just like entityManager
    public UserService(JdbcUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerUser(String username, String password, String role) {
        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(role)
                .build();
        userDetailsManager.createUser(user);
    }
}
