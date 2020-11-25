package com.internship.changeit.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserLogoService {

    void saveUserLogo(final Long userId, final MultipartFile file);

    void renderUserLogoFromDb(final Long userId, final HttpServletResponse response) throws IOException;

    void deleteUserLogo(final Long userId);
}
