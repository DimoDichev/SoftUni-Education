package com.softuni.pathfinderapp.web;

import com.softuni.pathfinderapp.model.binding.PictureUploadBindingModel;
import com.softuni.pathfinderapp.model.binding.RouteAddBindingModel;
import com.softuni.pathfinderapp.model.view.RouteDetailsViewModel;
import com.softuni.pathfinderapp.model.view.RouteViewModel;
import com.softuni.pathfinderapp.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {
        model.addAttribute("routes", routeService.findAllRoutesViewModel());
        return "routes";
    }

    @GetMapping("/details/{id}")
    public String routeDetails(@PathVariable Long id, Model model) {
        model.addAttribute("route", routeService.findRouteDetailsViewById(id));
        return "route-details";
    }

    @GetMapping("/add")
    public String routeAdd() {
        return "add-route";
    }

    @PostMapping("/add")
    public String routeAdd(@Valid RouteAddBindingModel routeAddBindingModel, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,  @AuthenticationPrincipal UserDetails currentUser) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeAddBindingModel", routeAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);

            return "redirect:add";
        }

        routeService.save(routeAddBindingModel, currentUser);

        return "redirect:/";
    }

    @PostMapping("/upload-picture")
    public String uploadPicture(@Valid PictureUploadBindingModel pictureUploadBindingModel,
                                @AuthenticationPrincipal UserDetails currentUser) {
        routeService.uploadPicture(pictureUploadBindingModel, currentUser);
        return "redirect:/routes/all";
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }

}
