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
@AllArgsConstructor
@RequestMapping("api/v1/dislikes")
public class DislikesController {

    private final DislikesService dislikesService;

    @GetMapping("/comment/{commentId}/user/{userId}")
    public DislikesDto getDislike(@PathVariable Long commentId, @PathVariable Long userId) {
        return DislikesMapper.INSTANCE.toDto(dislikesService.getDislike(commentId, userId));
    }

    @PostMapping
    public DislikesDto dislike(@RequestBody DislikesDto dislikesDto) {
        final Dislikes dislikes = DislikesMapper.INSTANCE.fromDto(dislikesDto);
        return DislikesMapper.INSTANCE.toDto(dislikesService.saveDislike(dislikes));
    }

    @DeleteMapping("/comment/{commentId}/user/{userId}")
    public void deleteDislike(@PathVariable Long commentId, @PathVariable Long userId){
        dislikesService.deleteDislike(commentId, userId);
    }
}
