package com.internship.changeit.service;

import com.internship.changeit.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    User getUserByEmail(String email);

    boolean isEmailUnique(String email);

    void registerNewUser(User user);

    void createVerificationToken(User user, String token);
}
