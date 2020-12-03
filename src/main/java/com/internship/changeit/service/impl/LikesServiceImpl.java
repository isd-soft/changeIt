package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Likes;
import com.internship.changeit.repository.LikesRepository;
import com.internship.changeit.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;

    @Override
    public Likes saveLike(Likes likes) {
        if (
                likesRepository.findAll()
                        .stream()
                        .anyMatch(like -> like.getComment().equals(likes.getComment())
                                && like.getUser().equals(likes.getUser()))
        ) {
            return new Likes();
        } else {
            return likesRepository.save(likes);
        }
    }

    @Override
    public Long getByComment(Long id) {
        return likesRepository.findAll()
                .stream()
                .filter(like -> like.getComment().getComment_id().equals(id))
                .count();
    }

    @Override
    public Likes getLike(Long commentId, Long userId) {
        return likesRepository.findAll()
                .stream()
                .filter(like -> like.getComment().getComment_id().equals(commentId) && like.getUser().getUser_id().equals(userId) )
                .findAny().orElse(null);
    }

    @Override
    public void deleteLike(Long commentId, Long userId) {
        Likes likes = getLike(commentId, userId);
        likesRepository.findById(likes.getLike_id()).
                orElseThrow(() -> new ApplicationException(ExceptionType.VOTE_NOT_FOUND));
        likesRepository.deleteById(likes.getLike_id());
    }
}
