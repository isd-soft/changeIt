package com.internship.changeit.controller;

import com.internship.changeit.model.Comment;
import org.springframework.web.bind.annotation.*;
import com.internship.changeit.service.impl.CommentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/problem/{id}")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService){
        this.commentService = commentService;
    }

    @GetMapping
    List<Comment> all(){
        return commentService.getAllComments();
    }

    @PostMapping
    void newComment(@RequestBody Comment newComment){
        commentService.saveComment(newComment);
    }

    @DeleteMapping
    void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
