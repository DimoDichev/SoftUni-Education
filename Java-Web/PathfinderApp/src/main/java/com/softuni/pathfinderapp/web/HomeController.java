package com.softuni.pathfinderapp.web;

import com.softuni.pathfinderapp.repository.PictureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final PictureRepository pictureRepository;

    public HomeController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pictures", pictureRepository.findLast10Pictures());
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
