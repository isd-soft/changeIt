package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.User;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
          return userRepository.findById(id).
                  orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).
                orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }
}
