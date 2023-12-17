package com.example.resellerapp.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.EAGER)
    private Set<OfferEntity> offers;
    @OneToMany(mappedBy = "boughtBy", fetch = FetchType.EAGER)
    private Set<OfferEntity> boughtOffers;

    public UserEntity() {
        this.offers = new HashSet<>();
        this.boughtOffers = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OfferEntity> getOffers() {
        return offers;
    }

    public void setOffers(Set<OfferEntity> offers) {
        this.offers = offers;
    }

    public Set<OfferEntity> getBoughtOffers() {
        return boughtOffers;
    }

    public void setBoughtOffers(Set<OfferEntity> boughtOffers) {
        this.boughtOffers = boughtOffers;
    }
}
