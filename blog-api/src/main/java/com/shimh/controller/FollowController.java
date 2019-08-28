package com.shimh.controller;

import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.service.FollowService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MDC
 * @version 1.0
 * @since 8/28/2019
 **/
@RestController
@RequestMapping(value = "/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping
    @RequiresAuthentication
    @LogAnnotation(module = "关注", operation = "关注用户")
    public Result followUser(@Validated @RequestBody Long userId, Long followerId) {
        boolean state = followService.follow(userId, followerId);
        if (state) {
            return Result.success();
        } else {
            return Result.error(ResultCode.DATA_ALREADY_EXISTED, "Already followed or other inner error");
        }
    }

    @PostMapping("/unfollow")
    @RequiresAuthentication
    @LogAnnotation(module = "取消关注", operation = "取关用户")
    public Result unfollowUser(@Validated @RequestBody Long userId, Long unfollowerId) {
        boolean state = followService.unfollow(userId, unfollowerId);
        if (state) {
            return Result.success();
        } else {
            return Result.error(ResultCode.DATA_ALREADY_EXISTED, "Already followed or other inner error");
        }
    }
}
