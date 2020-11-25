package com.internship.changeit.service;

import com.internship.changeit.dto.ImageDto;
import com.internship.changeit.model.Problem;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ImageService {

    void updateImage(final Long imageId, final MultipartFile file);

    List<ImageDto> renderImageFromDb(final Long problemId, final HttpServletResponse response) throws IOException;

    void deleteImage(final Long problemId, final Long imageId);

    void uploadImage(final Problem problem, final MultipartFile file);

    boolean isImageNumberExceeded(final Problem problem, final Integer numberOfImages);
}
