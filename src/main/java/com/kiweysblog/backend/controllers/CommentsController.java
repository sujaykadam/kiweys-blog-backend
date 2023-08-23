package com.kiweysblog.backend.controllers;

import com.kiweysblog.backend.models.Comment;
import com.kiweysblog.backend.payload.ApiResponse;
import com.kiweysblog.backend.payload.CommentDto;
import com.kiweysblog.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
    @Autowired
    private CommentService commentService;
    @PostMapping("{postId}/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId, @PathVariable Integer userId){
        CommentDto commentDto = this.commentService.createComment(comment, postId, userId);
        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully!!", true), HttpStatus.OK);
    }
}