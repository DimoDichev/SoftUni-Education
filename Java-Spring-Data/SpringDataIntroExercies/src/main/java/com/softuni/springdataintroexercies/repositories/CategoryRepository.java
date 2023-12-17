package com.softuni.springdataintroexercies.repositories;

import com.softuni.springdataintroexercies.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
