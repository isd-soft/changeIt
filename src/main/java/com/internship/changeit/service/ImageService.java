package com.internship.changeit.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ImageService {

    void saveImageFile(final Long recipeId, final MultipartFile file);

    void renderImageFromDb(final Long problemId, final HttpServletResponse response) throws IOException;

    void deleteImage(final Long problemId);
}
