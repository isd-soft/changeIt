package com.internship.changeit.controller;

import com.internship.changeit.dto.CommentVoteDto;
import com.internship.changeit.mapper.CommentVoteMapper;
import com.internship.changeit.model.CommentVote;
import com.internship.changeit.service.CommentVoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment_vote")
@AllArgsConstructor
public class CommentVoteController {

    private final CommentVoteService commentVoteService;

    @GetMapping("/{commentId}/{userId}")
    CommentVoteDto getCommentVote(@PathVariable Long commentId, @PathVariable Long userId) {

        return CommentVoteMapper.INSTANCE.toDto(commentVoteService.getVote(commentId, userId));
    }

    @PostMapping
    CommentVoteDto vote(@RequestBody CommentVoteDto commentVoteDto) {
        CommentVote commentVote = CommentVoteMapper.INSTANCE.fromDto(commentVoteDto);
        return CommentVoteMapper.INSTANCE.toDto(commentVoteService.saveVote(commentVote));
    }

    @DeleteMapping("/{id}")
    void deleteCommentVote(@PathVariable Long id){
        commentVoteService.deleteCommentVote(id);
    }
}
