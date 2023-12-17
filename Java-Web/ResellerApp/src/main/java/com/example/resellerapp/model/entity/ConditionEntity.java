package com.example.resellerapp.model.entity;

import com.example.resellerapp.model.enums.ConditionNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "conditions")
public class ConditionEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private ConditionNameEnum name;
    @Column(nullable = false)
    private String description;

    public ConditionEntity() {
    }

    public ConditionNameEnum getName() {
        return name;
    }

    public void setName(ConditionNameEnum name) {
        this.name = name;
        this.descriptionInit(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void descriptionInit(ConditionNameEnum name) {
        switch (name) {
            case EXCELLENT -> setDescription("In perfect condition");
            case GOOD -> setDescription("Some signs of wear and tear or minor defects");
            case ACCEPTABLE -> setDescription("The item is fairly worn but continues to function properly");
        }
    }
}
