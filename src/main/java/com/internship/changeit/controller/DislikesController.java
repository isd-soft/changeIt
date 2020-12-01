package com.internship.changeit.controller;

import com.internship.changeit.dto.DislikesDto;
import com.internship.changeit.dto.LikesDto;
import com.internship.changeit.mapper.DislikesMapper;
import com.internship.changeit.mapper.LikesMapper;
import com.internship.changeit.model.Dislikes;
import com.internship.changeit.model.Likes;
import com.internship.changeit.service.DislikesService;
import com.internship.changeit.service.LikesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dislikes")
@AllArgsConstructor
public class DislikesController {

    private final DislikesService dislikesService;

    @GetMapping("/{commentId}/{userId}")
    DislikesDto getDislike(@PathVariable Long commentId, @PathVariable Long userId) {
        return DislikesMapper.INSTANCE.toDto(dislikesService.getDislike(commentId, userId));
    }

    @PostMapping
    DislikesDto dislike(@RequestBody DislikesDto dislikesDto) {
        Dislikes dislikes = DislikesMapper.INSTANCE.fromDto(dislikesDto);
        return DislikesMapper.INSTANCE.toDto(dislikesService.saveDislike(dislikes));
    }

    @DeleteMapping("/{commentId}/{userId}")
    void deleteDislike(@PathVariable Long commentId, @PathVariable Long userId){
        dislikesService.deleteDislike(commentId, userId);
    }
}
