package com.mydg.controller;

import com.mydg.common.annotation.LogAnnotation;
import com.mydg.common.constant.ResultCode;
import com.mydg.common.result.Result;
import com.mydg.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bryantma
 * @version 1.0
 * @since 8/28/2019
 **/
@RestController
@RequestMapping(value = "/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/{userId}/{articleId}")
    // @RequiresAuthentication
    @LogAnnotation(module = "点赞", operation = "点赞文章")
    public Result likeArticle(@PathVariable("userId") Long userId, @PathVariable("articleId") Long articleId) {
        boolean state = likeService.like(userId, articleId);
        if (state) {
            return Result.success();
        } else {
            return Result.error(ResultCode.DATA_ALREADY_EXISTED, "Already liked or other inner error");
        }
    }

    @GetMapping("/unlike/{userId}/{articleId}")
    // @RequiresAuthentication
    @LogAnnotation(module = "取消赞", operation = "取消赞文章")
    public Result unLikeArticle(@PathVariable("userId") Long userId, @PathVariable("articleId") Long articleId) {
        boolean state = likeService.unlike(userId, articleId);
        if (state) {
            return Result.success();
        } else {
            return Result.error(ResultCode.DATA_ALREADY_EXISTED, "Already liked or other inner error");
        }
    }

    @GetMapping("/isLiking/{userId}/{articleId}")
    // @RequiresAuthentication
    @LogAnnotation(module = "点赞", operation = "点赞文章")
    public Result isLiking(@PathVariable("userId") Long userId, @PathVariable("articleId") Long articleId) {
        boolean state = likeService.hasLiked(userId, articleId);
        return Result.success(state);
    }
}
