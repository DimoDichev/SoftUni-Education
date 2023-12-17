package com.softuni.mobileleapp.model.entity;

import com.softuni.mobileleapp.model.enums.UserRolesTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private UserRolesTypeEnum userRolesType;

    public UserRole() {
    }

    public UserRolesTypeEnum getUserRolesType() {
        return userRolesType;
    }

    public void setUserRolesType(UserRolesTypeEnum userRolesType) {
        this.userRolesType = userRolesType;
    }

}
