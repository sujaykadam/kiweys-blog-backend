package com.kiweysblog.backend.repositories;

import com.kiweysblog.backend.models.Category;
import com.kiweysblog.backend.models.Post;
import com.kiweysblog.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findAllByAuthor(User user);
    List<Post> findAllByCategory(Category category);

}
