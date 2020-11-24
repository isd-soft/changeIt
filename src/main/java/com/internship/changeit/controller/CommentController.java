package com.internship.changeit.controller;

import com.internship.changeit.dto.CommentDto;
import com.internship.changeit.dto.ProblemDto;
import com.internship.changeit.mapper.CommentMapper;
import com.internship.changeit.mapper.ProblemMapper;
import com.internship.changeit.model.Comment;
import com.internship.changeit.service.CommentService;
import com.internship.changeit.service.CommentVoteService;
import com.internship.changeit.service.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentVoteService commentVoteService;

    public CommentController(CommentServiceImpl commentService, CommentVoteService commentVoteService){
        this.commentService = commentService;
        this.commentVoteService = commentVoteService;
    }

    @GetMapping
    public List<CommentDto> getAllComments(){
        return commentService.getAllComments().stream()
                .map(CommentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    CommentDto one(@PathVariable Long id) {
        return CommentMapper.INSTANCE.toDto(commentService.getCommentById(id));
    }

    @GetMapping("/{id}/votes")
    Long getVotesByComment(@PathVariable Long id) {
        return commentVoteService.getByComment(id);
    }

    @PostMapping
    public CommentDto createComment(@RequestBody CommentDto commentDto){
        Comment comment = CommentMapper.INSTANCE.fromDto(commentDto);
        commentService.saveComment(comment);
        return commentDto;
    }

    @PutMapping("/{id}")
    public CommentDto updateComment(@RequestBody CommentDto newCommentDto, @PathVariable Long id){
        Comment newComment = CommentMapper.INSTANCE.fromDto(newCommentDto);
        commentService.updateComment(newComment, id);
        return newCommentDto;
    }

    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
