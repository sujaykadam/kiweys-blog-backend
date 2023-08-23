package com.kiweysblog.backend.services.impl;

import com.kiweysblog.backend.exceptions.ResourceNotFoundException;
import com.kiweysblog.backend.models.Comment;
import com.kiweysblog.backend.models.Post;
import com.kiweysblog.backend.models.User;
import com.kiweysblog.backend.payload.CommentDto;
import com.kiweysblog.backend.repositories.CommentRepo;
import com.kiweysblog.backend.repositories.PostRepo;
import com.kiweysblog.backend.repositories.UserRepo;
import com.kiweysblog.backend.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentsDto, Integer postId, Integer userId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post ID: ", postId));
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User ID: ", userId));
        Comment comment = this.modelMapper.map(commentsDto, Comment.class);
        comment.setPost(post);
        comment.setAuthor(user);
        Comment savedComment = this.commentsRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentsRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment ID: ", commentId));
        this.commentsRepo.delete(comment);
    }
}
