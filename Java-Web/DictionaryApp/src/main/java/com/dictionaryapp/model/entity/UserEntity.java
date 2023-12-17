package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "addedBy")
    private List<WordEntity> addedWords;

    public UserEntity() {
        this.addedWords = new ArrayList<>();
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

    public List<WordEntity> getAddedWords() {
        return addedWords;
    }

    public void setAddedWords(List<WordEntity> addedWords) {
        this.addedWords = addedWords;
    }
}
