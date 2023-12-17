package com.example.resellerapp.controller;

import com.example.resellerapp.model.dto.OfferAddDto;
import com.example.resellerapp.service.OfferService;
import com.example.resellerapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("offers")
public class OfferController {

    private final OfferService offerService;
    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/add")
    public String add() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "offer-add";
    }

    @PostMapping("/add")
    public String add(@Valid OfferAddDto offerAddDto,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddDto", offerAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddDto", bindingResult);
            return "redirect:add";
        }

        offerService.add(offerAddDto);

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        offerService.remove(id);
        return "redirect:/home";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        offerService.buy(id, loggedUser.getUsername());
        return "redirect:/home";
    }

    @ModelAttribute
    public OfferAddDto offerAddDto() {
        return new OfferAddDto();
    }

}
