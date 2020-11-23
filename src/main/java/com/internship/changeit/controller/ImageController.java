package com.internship.changeit.controller;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/problem")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/{problemId}/image")
    public ResponseEntity<?> uploadProblemImage(@PathVariable final String problemId, @RequestParam("imageFile") final MultipartFile file){
        if(file.isEmpty()) {
            throw new ApplicationException(ExceptionType.FILE_NOT_FOUND);
        }

        imageService.saveImageFile(Long.valueOf(problemId), file);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("{problemId}/image")
    public void renderProblemImage(@PathVariable final String problemId, final HttpServletResponse response) throws IOException {
        imageService.renderImageFromDb(Long.valueOf(problemId), response);
    }

    @DeleteMapping("{problemId}/image")
    public ResponseEntity<?> deleteProblemImage(@PathVariable final String problemId){
        imageService.deleteImage(Long.valueOf(problemId));
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
