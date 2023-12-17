package com.softuni.pathfinderapp.model.binding;

import com.softuni.pathfinderapp.validation.annotation.FileAnnotation;
import org.springframework.web.multipart.MultipartFile;

public class PictureUploadBindingModel {

    private Long routeId;
    @FileAnnotation(contentTypes = {"image/png", "image/jpeg"})
    private MultipartFile picture;

    public PictureUploadBindingModel() {
    }

    public Long getRouteId() {
        return routeId;
    }

    public PictureUploadBindingModel setRouteId(Long routeId) {
        this.routeId = routeId;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public PictureUploadBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
