package com.kiweysblog.backend.services.impl;

import com.kiweysblog.backend.exceptions.ResourceNotFoundException;
import com.kiweysblog.backend.models.Category;
import com.kiweysblog.backend.models.Post;
import com.kiweysblog.backend.models.User;
import com.kiweysblog.backend.payload.PostDto;
import com.kiweysblog.backend.repositories.CategoryRepo;
import com.kiweysblog.backend.repositories.PostRepo;
import com.kiweysblog.backend.repositories.UserRepo;
import com.kiweysblog.backend.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer authorId, Integer categoryId) {

        User user = this.userRepo.findById(authorId)
                .orElseThrow(()-> new ResourceNotFoundException("User ","user ID", authorId ));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category ","category ID", categoryId ));

        Post post = this.modelMapper.map(postDto, Post.class);
        if(Objects.isNull(postDto.getImageName()) || postDto.getImageName().equals("")){
            post.setImageName("default.png");
        } else {
            post.setImageName(postDto.getImageName());
        }
        Date date = new Date();
        post.setCreatedDate(date);
        post.setModifiedDate(date);
        post.setAuthor(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);
         return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        if (!Objects.isNull(postDto.getTitle()) && !postDto.getTitle().equals(""))
            post.setTitle(postDto.getTitle());
        if (!Objects.isNull(postDto.getContent()) && !postDto.getContent().equals(""))
            post.setContent(postDto.getContent());
        if (!Objects.isNull(postDto.getImageName()) && !postDto.getImageName().equals(""))
            post.setImageName(postDto.getImageName());
        post.setModifiedDate(new Date());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepo.findAll();
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
        List<Post> posts = this.postRepo.findAllByCategory(category);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer authorId) {
        User user = this.userRepo.findById(authorId)
                .orElseThrow(()-> new ResourceNotFoundException("Author", "ID", authorId));
        List<Post> posts = this.postRepo.findAllByAuthor(user);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }
}
