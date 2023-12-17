package com.softuni.pathfinderapp.model.entity;

import com.softuni.pathfinderapp.model.enums.RoleTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private RoleTypeEnum name;

    public RoleEntity() {
    }

    public RoleTypeEnum getName() {
        return name;
    }

    public void setName(RoleTypeEnum name) {
        this.name = name;
    }
}
