package com.kiweysblog.backend.repositories;

import com.kiweysblog.backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
