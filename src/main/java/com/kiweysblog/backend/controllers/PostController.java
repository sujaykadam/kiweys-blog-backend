package com.kiweysblog.backend.controllers;

import com.kiweysblog.backend.config.AppConstants;
import com.kiweysblog.backend.payload.ApiResponse;
import com.kiweysblog.backend.payload.PostDto;
import com.kiweysblog.backend.payload.PostResponse;
import com.kiweysblog.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping("")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @RequestParam Integer authorId,
            @RequestParam Integer categoryId
        ){
            PostDto createdPost = this.postService.createPost(postDto, authorId, categoryId);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer authorId){
        List<PostDto> posts = this.postService.getPostsByUser(authorId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(defaultValue = AppConstants.PAGE) Integer page,
            @RequestParam(defaultValue = AppConstants.SIZE) Integer size,
            @RequestParam(defaultValue = AppConstants.SORT_POSTS_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.ORDER_POSTS_BY) String orderBy
        ){
            PostResponse posts = this.postService.getPosts(page, size, sortBy, orderBy);
            return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId){
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@RequestParam(defaultValue = AppConstants.DEFAULT_BLANK_STRING) String keyWord){
        List<PostDto> searchedPosts = this.postService.searchPost(keyWord);
        return new ResponseEntity<>(searchedPosts, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto post = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }
}
