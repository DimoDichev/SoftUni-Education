package com.example.resellerapp.service.impl;

import com.example.resellerapp.model.dto.OfferAddDto;
import com.example.resellerapp.model.entity.ConditionEntity;
import com.example.resellerapp.model.entity.OfferEntity;
import com.example.resellerapp.model.entity.UserEntity;
import com.example.resellerapp.model.view.OfferViewDto;
import com.example.resellerapp.repository.ConditionRepository;
import com.example.resellerapp.repository.OfferRepository;
import com.example.resellerapp.repository.UserRepository;
import com.example.resellerapp.service.OfferService;
import com.example.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public OfferServiceImpl(OfferRepository offerRepository, ConditionRepository conditionRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public void add(OfferAddDto offerAddDto) {
        ConditionEntity condition = conditionRepository.findByName(offerAddDto.getCondition()).orElse(null);
        UserEntity user = userRepository.findByUsername(loggedUser.getUsername()).orElse(null);

        if (condition != null) {
            OfferEntity offer = new OfferEntity();
            offer.setDescriptions(offerAddDto.getDescriptions());
            offer.setPrice(offerAddDto.getPrice());
            offer.setCondition(condition);
            offer.setCreatedBy(user);
            offerRepository.save(offer);
        }
    }

    @Override
    public void remove(Long id) {
        if (id != null) {
            offerRepository.deleteById(id);
        }
    }

    @Override
    public void buy(Long id, String username) {
        UserEntity buyer = userRepository.findByUsername(username).orElse(null);
        OfferEntity offer = offerRepository.findById(id).orElse(null);

        if (buyer != null && offer != null) {
            offer.setBoughtBy(buyer);
            offerRepository.save(offer);
        }
    }

    @Override
    public List<OfferEntity> getAllOffers() {
        return offerRepository.findAll();
    }
}
