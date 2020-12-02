package com.internship.changeit.controller;

import com.internship.changeit.dto.CommentDto;
import com.internship.changeit.mapper.CommentMapper;
import com.internship.changeit.model.Comment;
import com.internship.changeit.service.CommentService;
import com.internship.changeit.service.DislikesService;
import com.internship.changeit.service.LikesService;
import com.internship.changeit.service.impl.CommentServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;
    private final LikesService likesService;
    private final DislikesService dislikesService;

    public CommentController(CommentServiceImpl commentService, LikesService likesService, DislikesService dislikesService){
        this.commentService = commentService;
        this.likesService = likesService;
        this.dislikesService = dislikesService;
    }

    @GetMapping
    public List<CommentDto> getAllComments(){
        return commentService.getAllComments().stream()
                .map(CommentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CommentDto one(@PathVariable Long id) {
        return CommentMapper.INSTANCE.toDto(commentService.getCommentById(id));
    }

    @GetMapping("/{id}/likes")
    public Long getLikesByComment(@PathVariable Long id) {
        return likesService.getByComment(id);
    }

    @GetMapping("/{id}/dislikes")
    public Long getDislikesByComment(@PathVariable Long id) {
        return dislikesService.getByComment(id);
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
