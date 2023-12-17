package com.softuni.pathfinderapp.repository;

import com.softuni.pathfinderapp.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

    @Query(value = "SELECT p.url FROM pictures p ORDER BY id DESC LIMIT 8", nativeQuery = true)
    List<String> findLast10Pictures();

}
