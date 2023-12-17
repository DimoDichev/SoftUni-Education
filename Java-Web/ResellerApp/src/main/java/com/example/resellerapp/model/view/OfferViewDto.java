package com.example.resellerapp.model.view;

import com.example.resellerapp.model.entity.ConditionEntity;
import com.example.resellerapp.model.entity.UserEntity;

import java.math.BigDecimal;

public class OfferViewDto {
    private Long id;

    private String descriptions;
    private BigDecimal price;
    private ConditionEntity condition;
    private UserEntity createdBy;

    public OfferViewDto() {
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConditionEntity getCondition() {
        return condition;
    }

    public void setCondition(ConditionEntity condition) {
        this.condition = condition;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }
}
