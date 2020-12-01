package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Dislikes;
import com.internship.changeit.repository.DislikesRepository;
import com.internship.changeit.service.DislikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DislikesServiceImpl implements DislikesService {

    private final DislikesRepository dislikesRepository;

    @Override
    public Dislikes saveDislike(Dislikes dislikes) {
        if (
                dislikesRepository.findAll()
                        .stream()
                        .anyMatch(dislike -> dislike.getComment().equals(dislikes.getComment())
                                && dislike.getUser().equals(dislikes.getUser()))
        ) {
            return new Dislikes();
        } else {
            return dislikesRepository.save(dislikes);
        }
    }

    @Override
    public Long getByComment(Long id) {
        return dislikesRepository.findAll()
                .stream()
                .filter(dislike -> dislike.getComment().getComment_id().equals(id))
                .count();
    }

    @Override
    public Dislikes getDislike(Long commentId, Long userId) {
        return dislikesRepository.findAll()
                .stream()
                .filter(dislike -> dislike.getComment().getComment_id().equals(commentId) && dislike.getUser().getUser_id().equals(userId) )
                .findAny().orElse(null);
    }

    @Override
    public void deleteDislike(Long commentId, Long userId) {
        Dislikes dislikes = getDislike(commentId, userId);
        dislikesRepository.findById(dislikes.getDislike_id()).
                orElseThrow(() -> new ApplicationException(ExceptionType.VOTE_NOT_FOUND));
        dislikesRepository.deleteById(dislikes.getDislike_id());
    }
}
