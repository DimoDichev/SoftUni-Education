package com.softuni.pathfinderapp.repository;

import com.softuni.pathfinderapp.model.entity.RoleEntity;
import com.softuni.pathfinderapp.model.enums.RoleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(RoleTypeEnum name);

}
