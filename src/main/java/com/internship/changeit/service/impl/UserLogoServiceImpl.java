package com.internship.changeit.service.impl;

import com.internship.changeit.dto.UserLogoDto;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.mapper.UserLogoMapper;
import com.internship.changeit.model.User;
import com.internship.changeit.model.UserLogo;
import com.internship.changeit.repository.UserLogoRepository;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.service.UserLogoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UserLogoServiceImpl implements UserLogoService {

    private final UserLogoRepository userLogoRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void updateUserLogo(Long userLogoId, MultipartFile file) {
        try {
            final UserLogo userLogo = userLogoRepository.findById(userLogoId).orElseThrow(
                    () -> new ApplicationException(ExceptionType.FILE_NOT_FOUND));

            byte[] byteObject = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }
            userLogo.setUserLogoFile(byteObject);
            this.userLogoRepository.save(userLogo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserLogoDto getUserLogo(Long userId){
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        return UserLogoMapper.INSTANCE.toDto(user.getUserLogo());
    }

    @Override
    @Transactional
    public void deleteUserLogo(Long userId, Long userLogoId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        this.userRepository.deleteById(userId);

        this.userRepository.save(user);
    }

    @Override
    @Transactional
    public void uploadUserLogo(final User user, final MultipartFile file) {
        if(file.getContentType() == null)
            throw new ApplicationException(ExceptionType.FILE_NOT_FOUND);

        try {
            byte[] byteObject = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }
            UserLogo userLogo = new UserLogo();
            userLogo.setUserLogoFile(byteObject);
            userLogo.setUser(user);
            this.userLogoRepository.save(userLogo);

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
