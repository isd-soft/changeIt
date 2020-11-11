package com.internship.changeit.controller;

import com.internship.changeit.dto.CommentDto;
import com.internship.changeit.mapper.CommentMapper;
import com.internship.changeit.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.internship.changeit.service.impl.CommentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/problem/{id}")
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDto> getAll(){
        ListIterator<Comment> comments = commentService.getAllComments().listIterator();
        List<CommentDto> commentDtos = new ArrayList<>();
        while(comments.hasNext()){
            commentDtos.add(commentMapper.toDto(comments.next()));
        }
        return commentDtos;
    }

    @PostMapping
    public CommentDto createComment(@RequestBody CommentDto newCommentDto){
        Comment comment = commentMapper.fromDto(newCommentDto);
        Comment commentCreated = commentService.saveComment(comment);
        return commentMapper.toDto(commentCreated);
    }

    @DeleteMapping
    void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
