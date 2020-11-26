package com.internship.changeit.service;

import com.internship.changeit.dto.UserDto;
import com.internship.changeit.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserLogoService {

    /*void saveUserLogo(final Long userId, final MultipartFile file);

    void renderUserLogoFromDb(final Long userId, final HttpServletResponse response) throws IOException;

    void deleteUserLogo2(final Long userId);

    public User store(MultipartFile file, Long id) throws IOException;

    public byte[] getFile(Long id);*/

    void updateUserLogo(final Long userId, final MultipartFile file);

    UserDto getUserLogo(final Long userId) throws IOException;

    void deleteUserLogo(final Long userId);

    void uploadUserLogo(final User user, final MultipartFile file);


}
