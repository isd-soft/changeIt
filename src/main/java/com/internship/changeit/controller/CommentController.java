package com.internship.changeit.controller;

import com.internship.changeit.dto.CommentDto;
import com.internship.changeit.dto.DomainDto;
import com.internship.changeit.mapper.CommentMapper;
import com.internship.changeit.mapper.DomainMapper;
import com.internship.changeit.model.Comment;
import com.internship.changeit.service.CommentService;
import com.internship.changeit.service.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentServiceImpl commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDto> getAllComments(){
        return commentService.getAllComments().stream()
                .map(CommentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentDto commentDto){
        Comment comment = CommentMapper.INSTANCE.fromDto(commentDto);
        commentService.saveComment(comment);
        return commentService.saveComment(comment);
    }
}
