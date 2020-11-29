package com.internship.changeit.service;

import com.internship.changeit.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserLogoService {

    void saveUserLogo(final Long userId, final MultipartFile file);

    void deleteUserLogo(final Long userId);

    UserDto getUserLogo(final Long userId);

}
