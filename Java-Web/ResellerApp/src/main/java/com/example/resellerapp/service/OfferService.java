package com.example.resellerapp.service;

import com.example.resellerapp.model.dto.OfferAddDto;
import com.example.resellerapp.model.entity.OfferEntity;

import java.util.List;

public interface OfferService {
    void add(OfferAddDto offerAddDto);

    void remove(Long id);

    void buy(Long id, String username);

    List<OfferEntity> getAllOffers();
}
