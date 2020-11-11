package com.internship.changeit.service;

import com.internship.changeit.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    User getUserByEmail(String email);

    boolean isEmailUnique(String email);

    void updateUser(User user);

    void registerUser(User user);

}
