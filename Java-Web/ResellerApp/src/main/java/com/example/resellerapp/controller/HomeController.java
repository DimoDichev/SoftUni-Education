package com.example.resellerapp.controller;

import com.example.resellerapp.model.entity.OfferEntity;
import com.example.resellerapp.model.view.OfferHomeViewDto;
import com.example.resellerapp.model.view.OfferViewDto;
import com.example.resellerapp.service.OfferService;
import com.example.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final OfferService offerService;

    public HomeController(LoggedUser loggedUser, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:home";
        }

        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        List<OfferEntity> allOffers = offerService.getAllOffers();

        List<OfferViewDto> allOtherOffers = new ArrayList<>();
        List<OfferViewDto> allUserOffers = new ArrayList<>();
        List<OfferViewDto> allUserBoughtOffers = new ArrayList<>();

        for (OfferEntity offer : allOffers) {

            OfferViewDto offerViewDto = new OfferViewDto();
            offerViewDto.setCreatedBy(offer.getCreatedBy());
            offerViewDto.setId(offer.getId());
            offerViewDto.setDescriptions(offer.getDescriptions());
            offerViewDto.setPrice(offer.getPrice());
            offerViewDto.setCondition(offer.getCondition());

            if (offer.getCreatedBy().getUsername().equals(loggedUser.getUsername()) && offer.getBoughtBy() == null) {
                allUserOffers.add(offerViewDto);
            } else if (!offer.getCreatedBy().getUsername().equals(loggedUser.getUsername()) && offer.getBoughtBy() == null) {
                allOtherOffers.add(offerViewDto);
            } else if (!offer.getCreatedBy().getUsername().equals(loggedUser.getUsername()) && offer.getBoughtBy().getUsername().equals(loggedUser.getUsername())) {
                allUserBoughtOffers.add(offerViewDto);
            }
        }

        OfferHomeViewDto offers = new OfferHomeViewDto();
        offers.setAllOffers(allOtherOffers);
        offers.setUserOffers(allUserOffers);
        offers.setUserBoughtOffers(allUserBoughtOffers);

        return new ModelAndView("/home", "offers", offers);
    }

}
