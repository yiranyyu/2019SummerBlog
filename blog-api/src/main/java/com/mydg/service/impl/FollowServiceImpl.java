package com.mydg.service.impl;

import com.mydg.entity.Follow;
import com.mydg.repository.FollowRepository;
import com.mydg.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Override
    public boolean isFollowing(Long userId, Long followerId) {
        Follow found = followRepository.findByUserIdAndFollowerId(userId, followerId);
        return found != null;
    }

    @Override
    public List<Follow> getMyFollowing(Long userId) {
        return followRepository.findAllByUserId(userId);
    }

    @Override
    public List<Follow> getMyFans(Long userId) {
        return followRepository.findAllByFollowerId(userId);
    }


    @Override
    @Transactional
    public boolean follow(Long userId, Long followerId) {
        if (!isFollowing(userId, followerId)) {
            Follow follow = new Follow(userId, followerId);
            follow.setDateCreated(new Date());
            followRepository.save(follow);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean unfollow(Long userId, Long followerId) {
        if (isFollowing(userId, followerId)) {
            followRepository.deleteAllByUserIdAndFollowerId(userId, followerId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Long saveFollow(Follow follow) {
        return followRepository.save(follow).getId();
    }

    @Override
    public void deleteFollowById(Long id) {
        followRepository.delete(id);
    }

    @Override
    public Follow getFollowById(Long id) {
        return followRepository.getOne(id);
    }
}
