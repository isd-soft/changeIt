package com.internship.changeit.model;

import java.util.ArrayList;
import java.util.List;

public class CommentDao implements Dao<Comment>{

    private List<Comment> comments = new ArrayList<>();

    @Override
    public List<Comment> getAll() {
        return comments;
    }

    @Override
    public void save(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void delete(Comment comment) {
        comments.remove(comment);
    }
}
