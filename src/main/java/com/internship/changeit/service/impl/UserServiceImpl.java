package com.internship.changeit.service.impl;

import com.internship.changeit.config.AppConfigBean;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Role;
import com.internship.changeit.model.User;
import com.internship.changeit.model.UserStatus;
import com.internship.changeit.model.VerificationToken;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.repository.VerificationTokenRepo;
import com.internship.changeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepo verificationTokenRepo;
    private final BCryptPasswordEncoder encoder;
    private final AppConfigBean appConfigBean;

    @Override
    @PreAuthorize("hasAnyAuthority('user:crud')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('user:crud')")
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public boolean isEmailUnique(final String email) {
        return this.getUserByEmail(email) == null;
    }

    @Override
    public void registerNewUser(final User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(final User user, final String token) {
        final VerificationToken userVerificationToken = new VerificationToken(token, user);
        verificationTokenRepo.save(userVerificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(final String email) {
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));

        final VerificationToken verificationToken = new VerificationToken();
        final String token = UUID.randomUUID().toString();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepo.save(verificationToken);

        return verificationToken;
    }

    @Override
    public SimpleMailMessage constructResetPasswordEmail(final String token, final User user) {
        final String url = appConfigBean.getAppUrl() + "/user/savePassword?id=" + user.getUser_id() + "&token=" + token;
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Reset Password");
        email.setText("Please open the following URL to reset your password: \r\n" + url);
        return email;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('user:crud')")
    public User updateUserStatus(final Long id, final UserStatus userStatus) {
        final User user = this.userRepository.findById(id).orElseThrow(
                () -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        user.setUserStatus(userStatus);
        this.userRepository.save(user);
        return user;

    }
}
