package com.kiweysblog.backend.services;

import com.kiweysblog.backend.payload.CommentDto;


public interface CommentService {
    CommentDto createComment(CommentDto commentsDto, Integer postId, Integer userId);
    void deleteComment(Integer commentId);
}