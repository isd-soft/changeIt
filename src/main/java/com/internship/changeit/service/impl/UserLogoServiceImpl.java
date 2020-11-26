package com.internship.changeit.service.impl;

import com.internship.changeit.dto.UserDto;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.mapper.ImageMapper;
import com.internship.changeit.mapper.UserMapper;
import com.internship.changeit.model.Image;
import com.internship.changeit.model.Problem;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserLogoServiceImpl implements UserLogoService {

    private final UserRepository userRepository;

    /*@Override
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
    public void renderUserLogoFromDb(final Long userId, final HttpServletResponse response) throws IOException {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));

            if(user.getUserLogo() != null){
                byte[] image = new byte[user.getUserLogo().length];
                int i = 0;
                for(Byte b : user.getUserLogo()){
                    image[i++] = b;
                }
                response.setContentType("image/jpeg");
                InputStream inputStream =  new ByteArrayInputStream(image);
                IOUtils.copy(inputStream, response.getOutputStream());
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
    public User store(MultipartFile file, Long id) throws IOException {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            User updatable = optionalUser.get();
            updatable.setUserLogo(file.getBytes());
            userRepository.save(updatable);
            return updatable;
        } else throw new ApplicationException(ExceptionType.USER_NOT_FOUND);
    }

    @Override
    public byte[] getFile(Long id) {
        User user = userRepository.findById(id).get();
        return user.getUserLogo();
    }*/

    @Override
    public void updateUserLogo(Long userId, MultipartFile file) {
        try {
            final User user = userRepository.findById(userId).orElseThrow(
                    () -> new ApplicationException(ExceptionType.USER_NOT_FOUND));

            byte[] byteObject = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }
            user.setUserLogo(byteObject);
            this.userRepository.save(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDto getUserLogo(Long userId) throws IOException {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));

        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public void deleteUserLogo(Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        user.setUserLogo(null);
        this.userRepository.save(user);
    }

    @Override
    public void uploadUserLogo(User user, MultipartFile file) {
        if(file.getContentType() == null)
            throw new ApplicationException(ExceptionType.FILE_NOT_FOUND);

        try {
            byte[] byteObject = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }
            user.setUserLogo(byteObject);
            this.userRepository.save(user);

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
