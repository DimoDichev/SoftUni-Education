package com.softuni.pathfinderapp.model.binding;

import com.softuni.pathfinderapp.model.entity.UserEntity;
import com.softuni.pathfinderapp.model.enums.CategoryNameEnum;
import com.softuni.pathfinderapp.model.enums.LevelEnum;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class RouteAddBindingModel {

    @NotNull
    @Size(min = 5, max = 20, message = "Route name must be between 5 and 20 characters!")
    private String name;
    @NotNull
    @Size(min = 5, message = "Description muns be at least 5 characters!")
    private String description;
    private MultipartFile gpxCoordinates;
    @NotNull(message = "Please select a level")
    private LevelEnum level;
    @Size(min = 11, max = 11, message = "Invalid youtube url provided")
    private String videoUrl;
    @NotEmpty(message = "Please select a category/s")
    private Set<CategoryNameEnum> categories;
    private UserEntity author;

    public RouteAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public RouteAddBindingModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
