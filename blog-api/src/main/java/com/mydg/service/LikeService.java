package com.mydg.service;


import com.mydg.entity.Like;

import java.util.List;

/**
 * @author bryantma
 * @version 1.0
 * @since 8/28/2019
 **/
public interface LikeService {
    boolean like(Long userId, Long articleId);

    boolean unlike(Long userId, Long articleId);

    boolean hasLiked(Long userId, Long articleId);

    List<Like> getMyLikes(Long userId);

    List<Like> getArticleLikes(Integer articleId);

    //Long saveFollow(Like like);

    //Like getFollowById(Long id);

    //void deleteFollowById(Long id);
}

