package softuni.exam.models.dto;

import softuni.exam.models.entity.StarType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class StarSeedDto {

    @NotNull
    @Size(min = 6)
    private String description;

    @NotNull
    @Positive
    private Double lightYears;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    private StarType starType;

    @NotNull
    private Long constellation;

    public StarSeedDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLightYears() {
        return lightYears;
    }

    public void setLightYears(Double lightYears) {
        this.lightYears = lightYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public Long getConstellationId() {
        return constellation;
    }

    public void setConstellationId(Long constellationId) {
        this.constellation = constellationId;
    }
}
