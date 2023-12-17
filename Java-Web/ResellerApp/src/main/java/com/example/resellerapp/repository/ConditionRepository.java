package com.example.resellerapp.repository;

import com.example.resellerapp.model.entity.ConditionEntity;
import com.example.resellerapp.model.enums.ConditionNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionRepository extends JpaRepository<ConditionEntity, Long> {

    Optional<ConditionEntity> findByName(ConditionNameEnum name);

}
