package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Role;
import com.internship.changeit.model.User;
import com.internship.changeit.model.UserStatus;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
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

    @Override
    public boolean isEmailUnique(final String email) {
        return this.getUserByEmail(email) == null;
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void registerUser(User user) {
        if(isEmailUnique(user.getEmail())) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setUserStatus(UserStatus.ACTIVE);
            user.setRole(Role.USER);
            userRepository.save(user);
        }
    }
}
