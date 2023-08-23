package com.kiweysblog.backend.repositories;

import com.kiweysblog.backend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}