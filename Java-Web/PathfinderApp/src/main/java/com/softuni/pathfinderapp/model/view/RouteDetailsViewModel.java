package com.softuni.pathfinderapp.model.view;

import com.softuni.pathfinderapp.model.entity.PictureEntity;
import com.softuni.pathfinderapp.model.entity.UserEntity;
import com.softuni.pathfinderapp.model.enums.LevelEnum;

import java.util.List;
import java.util.Set;

public class RouteDetailsViewModel {

    private Long id;
    private String name;
    private String description;
    private LevelEnum level;
    private String gpxCoordinates;
    private Set<String> pictureUrl;
    private String videoUrl;
    private UserEntity author;

    public RouteDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public Set<String> getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(Set<String> pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }
}
