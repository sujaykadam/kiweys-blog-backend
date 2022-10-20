package com.kiweysblog.backend.repositories;

import com.kiweysblog.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}