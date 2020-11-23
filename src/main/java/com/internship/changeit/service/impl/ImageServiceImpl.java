package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Problem;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.service.ImageService;
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
public class ImageServiceImpl implements ImageService {

    private final ProblemRepository problemRepository;

    @Override
    @Transactional
    public void saveImageFile(final Long problemId, final MultipartFile file) {
        try {
            Problem problem = problemRepository.findById(problemId)
                    .orElseThrow(() -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));

            byte[] byteObject = new byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            problem.setImage(byteObject);
            problemRepository.save(problem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void renderImageFromDb(final Long problemId, final HttpServletResponse response) throws IOException {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));

        if(problem.getImage() != null){
            byte[] image = new byte[problem.getImage().length];
            int i = 0;
            for(Byte b : problem.getImage()){
                image[i++] = b;
            }

            response.setContentType("image/jpeg");
            InputStream inputStream =  new ByteArrayInputStream(image);
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @Override
    @Transactional
    public void deleteImage(final Long problemId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));
        problem.setImage(null);
        this.problemRepository.save(problem);
    }
}
