package com.internship.changeit.service;

import com.internship.changeit.model.Dislikes;

public interface DislikesService {

    Dislikes saveDislike(Dislikes dislikes);

    Long getByComment(Long id);

    Dislikes getDislike(Long commentId, Long userId);

    void deleteDislike(Long commentId, Long userId);
}
