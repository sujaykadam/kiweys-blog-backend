package com.kiweysblog.backend.services;

import com.kiweysblog.backend.payload.PostDto;
import com.kiweysblog.backend.payload.PostResponse;

import java.util.List;

public interface PostService {
     PostDto createPost(PostDto postDto, Integer authorId, Integer categoryId);
     PostDto updatePost(PostDto postDto, Integer id);
     void deletePost(Integer id);
     PostResponse getPosts(Integer page, Integer size, String sortBy, String orderBy);
     PostDto getPostById(Integer id);
     List<PostDto> getPostsByCategory(Integer categoryId);
     List<PostDto> getPostsByUser(Integer authorId);
     List<PostDto> searchPost(String keyword);
}
