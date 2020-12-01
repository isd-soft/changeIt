package com.internship.changeit.service;

import com.internship.changeit.model.Likes;

public interface LikesService {

    Likes saveLike(Likes likes);

    Long getByComment(Long id);

    Likes getLike(Long commentId, Long userId);

    void deleteLike(Long commentId, Long userId);
}
