package com.internship.changeit.controller;

import com.internship.changeit.dto.LikesDto;
import com.internship.changeit.mapper.LikesMapper;
import com.internship.changeit.model.Likes;
import com.internship.changeit.service.LikesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/likes")
public class LikesController {

    private final LikesService likesService;

    @GetMapping("/{commentId}/{userId}")
    public LikesDto getLike(@PathVariable Long commentId, @PathVariable Long userId) {
        return LikesMapper.INSTANCE.toDto(likesService.getLike(commentId, userId));
    }

    @PostMapping
    public LikesDto like(@RequestBody LikesDto likesDto) {
        Likes likes = LikesMapper.INSTANCE.fromDto(likesDto);
        return LikesMapper.INSTANCE.toDto(likesService.saveLike(likes));
    }

    @DeleteMapping("/{commentId}/{userId}")
    void deleteLike(@PathVariable Long commentId, @PathVariable Long userId){
        likesService.deleteLike(commentId, userId);
    }
}
