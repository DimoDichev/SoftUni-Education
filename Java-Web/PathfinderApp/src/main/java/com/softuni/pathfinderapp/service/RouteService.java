package com.softuni.pathfinderapp.service;

import com.softuni.pathfinderapp.model.binding.PictureUploadBindingModel;
import com.softuni.pathfinderapp.model.binding.RouteAddBindingModel;
import com.softuni.pathfinderapp.model.view.RouteDetailsViewModel;
import com.softuni.pathfinderapp.model.view.RouteViewModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.List;

public interface RouteService {
    List<RouteViewModel> findAllRoutesViewModel();

    RouteDetailsViewModel findRouteDetailsViewById(Long id);

    void save(RouteAddBindingModel routeAddBindingModel, UserDetails currentUser) throws IOException;

    void uploadPicture(PictureUploadBindingModel pictureUploadBindingModel, UserDetails currentUser);
}
