package com.example.resellerapp.model.dto;

import com.example.resellerapp.model.entity.ConditionEntity;
import com.example.resellerapp.model.enums.ConditionNameEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class OfferAddDto {
    @NotNull
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String descriptions;
    @NotNull
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;
    @NotNull(message = "You must select a condition")
    private ConditionNameEnum condition;

    public OfferAddDto() {
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

    public ConditionNameEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionNameEnum condition) {
        this.condition = condition;
    }
}
