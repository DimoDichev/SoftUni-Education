package com.example.resellerapp.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {
    @Column(nullable = false)
    private String descriptions;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne()
    private ConditionEntity condition;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity createdBy;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity boughtBy;

    public OfferEntity() {
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

    public UserEntity getBoughtBy() {
        return boughtBy;
    }

    public void setBoughtBy(UserEntity boughtBy) {
        this.boughtBy = boughtBy;
    }
}
