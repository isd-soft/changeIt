package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.User;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.service.UserLogoService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@AllArgsConstructor
public class UserLogoServiceImpl implements UserLogoService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void saveUserLogo(Long userId, MultipartFile file) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));

            byte[] byteObject = new byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            user.setUserLogo(byteObject);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void deleteUserLogo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        user.setUserLogo(null);
        this.userRepository.save(user);
    }

    @Override
    public void renderImageFromDb(Long userId, HttpServletResponse response) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));

        if (user.getUserLogo() != null) {
            byte[] userLogo = new byte[user.getUserLogo().length];
            int i = 0;
            for (Byte b : user.getUserLogo()) {
                userLogo[i++] = b;
            }

            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(userLogo);
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }

}

