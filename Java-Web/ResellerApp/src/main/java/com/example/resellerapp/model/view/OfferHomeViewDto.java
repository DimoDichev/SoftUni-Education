package com.example.resellerapp.model.view;

import java.util.ArrayList;
import java.util.List;

public class OfferHomeViewDto {

    private List<OfferViewDto> allOffers;
    private List<OfferViewDto> userOffers;
    private List<OfferViewDto> userBoughtOffers;

    public OfferHomeViewDto() {
        this.allOffers = new ArrayList<>();
        this.userOffers = new ArrayList<>();
        this.userBoughtOffers = new ArrayList<>();
    }

    public List<OfferViewDto> getAllOffers() {
        return allOffers;
    }

    public void setAllOffers(List<OfferViewDto> allOffers) {
        this.allOffers = allOffers;
    }

    public List<OfferViewDto> getUserOffers() {
        return userOffers;
    }

    public void setUserOffers(List<OfferViewDto> userOffers) {
        this.userOffers = userOffers;
    }

    public List<OfferViewDto> getUserBoughtOffers() {
        return userBoughtOffers;
    }

    public void setUserBoughtOffers(List<OfferViewDto> userBoughtOffers) {
        this.userBoughtOffers = userBoughtOffers;
    }
}
