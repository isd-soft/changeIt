package com.internship.changeit.controller;

import com.internship.changeit.dto.CommentDto;
import com.internship.changeit.mapper.CommentMapper;
import com.internship.changeit.model.Comment;
import com.internship.changeit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.internship.changeit.service.impl.CommentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/comment")
public class CommentController {

//    @Autowired
//    private CommentMapper commentMapper;

    private final CommentService commentService;

    public CommentController(CommentServiceImpl commentService){
        this.commentService = commentService;
    }

//    @GetMapping
//    public List<CommentDto> getAll(){
//        ListIterator<Comment> comments = commentService.getAllComments().listIterator();
//        List<CommentDto> commentDtos = new ArrayList<>();
//        while(comments.hasNext()){
//            commentDtos.add(commentMapper.toDto(comments.next()));
//        }
//        return commentDtos;
//    }

    @PostMapping
    public Comment createComment(@RequestBody CommentDto commentDto){
        Comment comment = CommentMapper.INSTANCE.fromDto(commentDto);
        commentService.saveComment(comment);
        return commentService.saveComment(comment);
    }
//
//    @DeleteMapping("/{id}")
//    void deleteComment(@PathVariable Long id){
//        commentService.deleteComment(id);
//    }
}
