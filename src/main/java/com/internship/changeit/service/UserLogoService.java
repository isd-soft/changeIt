package com.internship.changeit.service;

import com.internship.changeit.dto.UserLogoDto;
import com.internship.changeit.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserLogoService {

    void updateUserLogo(final Long userId, final MultipartFile file);

    List<UserLogoDto> getUserLogo(final Long userId) throws IOException;

    void deleteUserLogo(final Long userId, final Long userLogoId);

    void uploadUserLogo(final User user, final MultipartFile file);
}
