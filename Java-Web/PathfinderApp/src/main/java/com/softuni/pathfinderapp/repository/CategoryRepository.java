package com.softuni.pathfinderapp.repository;

import com.softuni.pathfinderapp.model.entity.CategoryEntity;
import com.softuni.pathfinderapp.model.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(CategoryNameEnum name);
    Set<CategoryEntity> getAllByNameIn(Set<CategoryNameEnum> name);

}
