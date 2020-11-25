package com.internship.changeit.service.impl;

import com.internship.changeit.dto.ImageDto;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.mapper.ImageMapper;
import com.internship.changeit.model.Image;
import com.internship.changeit.model.Problem;
import com.internship.changeit.repository.ImageRepository;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ProblemRepository problemRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public void uploadImage(final Problem problem, final MultipartFile file) {
        try {
            byte[] byteObject = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }
            Image image = new Image();
            image.setImageFile(byteObject);
            image.setProblem(problem);
            this.imageRepository.save(image);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    @Transactional
    public void updateImage(final Long imageId, final MultipartFile file) {
        try {
            final Image image = imageRepository.findById(imageId).orElseThrow(
                    () -> new ApplicationException(ExceptionType.FILE_NOT_FOUND));

            byte[] byteObject = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }
            image.setImageFile(byteObject);
            this.imageRepository.save(image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<ImageDto> getImages(final Long problemId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));

        return problem.getImages().stream()
                                  .map(ImageMapper.INSTANCE::toDto)
                                  .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteImage(final Long problemId, final Long imageId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));
        this.imageRepository.deleteById(imageId);

        problem.getImages().removeIf(image -> image.getId().equals(imageId));
        this.problemRepository.save(problem);
    }


    public boolean isImageNumberExceeded(final Problem problem, final Integer numberOfImages) {
        return problem.getImages().size() > 5 || (problem.getImages().size() + numberOfImages) > 5;
    }
}
