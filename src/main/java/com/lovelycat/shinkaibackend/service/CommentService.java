package com.lovelycat.shinkaibackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lovelycat.shinkaibackend.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {

    void postComment(Comment comment);

    List<Comment> getCommentsByCreationId(Long creationId);
}
