package com.kiweysblog.backend.services;

import com.kiweysblog.backend.payload.PostDto;
import java.util.List;

public interface PostService {
     PostDto createPost(PostDto postDto, Integer authorId, Integer categoryId);
     PostDto updatePost(PostDto postDto, Integer id);
     void deletePost(Integer id);
     List<PostDto> getAllPost();
     PostDto getPostById(Integer id);
     List<PostDto> getPostsByCategory(Integer categoryId);
     List<PostDto> getPostsByUser(Integer authorId);
     List<PostDto> searchPost(String keyword);
}
