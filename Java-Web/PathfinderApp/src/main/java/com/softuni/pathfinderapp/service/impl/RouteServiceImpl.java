package com.softuni.pathfinderapp.service.impl;

import com.softuni.pathfinderapp.exception.ObjectNotFountException;
import com.softuni.pathfinderapp.model.binding.PictureUploadBindingModel;
import com.softuni.pathfinderapp.model.binding.RouteAddBindingModel;
import com.softuni.pathfinderapp.model.entity.PictureEntity;
import com.softuni.pathfinderapp.model.entity.RouteEntity;
import com.softuni.pathfinderapp.model.view.RouteDetailsViewModel;
import com.softuni.pathfinderapp.model.view.RouteViewModel;
import com.softuni.pathfinderapp.repository.PictureRepository;
import com.softuni.pathfinderapp.repository.RouteRepository;
import com.softuni.pathfinderapp.repository.UserRepository;
import com.softuni.pathfinderapp.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository, UserRepository userRepository, PictureRepository pictureRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteViewModel> findAllRoutesViewModel() {
        return routeRepository
                .findAll()
                .stream()
                .map(r -> modelMapper.map(r, RouteViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RouteDetailsViewModel findRouteDetailsViewById(Long id) {
        RouteEntity route = routeRepository.findById(id).orElseThrow(ObjectNotFountException::new);

        return modelMapper.map(route, RouteDetailsViewModel.class);
    }

    @Override
    public void save(RouteAddBindingModel routeAddBindingModel, UserDetails currentUser) throws IOException {
        RouteEntity route = modelMapper.map(routeAddBindingModel, RouteEntity.class);
        route.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));
        route.setAuthor(userRepository.findByUsername(currentUser.getUsername()).orElseThrow(ObjectNotFountException::new));
        routeRepository.save(route);
    }

    @Override
    public void uploadPicture(PictureUploadBindingModel pictureUploadBindingModel, UserDetails currentUser) {
        MultipartFile pictureFile = pictureUploadBindingModel.getPicture();

        String picturePath = getPicturePath(pictureFile, currentUser);

//        try {
//            ClassLoader classLoader = getClass().getClassLoader();
//            File file = new File(classLoader.getResource(".").getFile() + "/" + picturePath);
//
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(pictureFile.getBytes());
//
//            Optional<RouteEntity> optionalRoute = routeRepository.findById(pictureUploadBindingModel.getRouteId());
//
//            if (optionalRoute.isPresent()) {
//                RouteEntity route = optionalRoute.get();
//
//                PictureEntity pictureEntity = new PictureEntity();
//                pictureEntity.setUrl("PathfinderApp/src/main/resources/routesImg" + picturePath);
//                pictureEntity.setTitle(route.getName());
//                pictureEntity.setRoute(route);
//
//                pictureRepository.save(pictureEntity);
//
//                route.getPictures().add(pictureEntity);
//                routeRepository.save(route);
//            }
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }

    }

    private String getPicturePath(MultipartFile pictureFile, UserDetails currentUser) {
        String[] splitPictureName = Objects.requireNonNull(pictureFile.getOriginalFilename()).split("\\.");
        String ext = splitPictureName[splitPictureName.length - 1];

        return currentUser.getUsername() + UUID.randomUUID() + "." + ext;
    }

}
