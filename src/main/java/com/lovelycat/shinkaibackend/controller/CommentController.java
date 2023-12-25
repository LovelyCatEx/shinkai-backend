package com.lovelycat.shinkaibackend.controller;

import com.alibaba.fastjson.JSON;
import com.lovelycat.shinkaibackend.entity.Comment;
import com.lovelycat.shinkaibackend.response.Result;
import com.lovelycat.shinkaibackend.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("/comments")
    public Result<?> getCreationComments(@RequestParam(name = "cid") Long creationId) {
        List<Comment> commentList = commentService.getCommentsByCreationId(creationId);
        if (commentList == null) {
            return Result.failed(Result.CODE_ERR_BAD_REQUEST, "Comments not found");
        } else {
            return Result.success(commentList);
        }
    }

    @PostMapping("/post")
    public Result<?> postNewComment(@RequestBody Comment comment) {
        comment.autoSetPublishedTime();
        commentService.postComment(comment);
        return Result.success("Unknown result");
    }


}
