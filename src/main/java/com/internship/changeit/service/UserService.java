package com.internship.changeit.service;

import com.internship.changeit.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUsersByEmail(String email);

    void deleteUser(Long id);

    boolean isEmailUnique(String email);
}
