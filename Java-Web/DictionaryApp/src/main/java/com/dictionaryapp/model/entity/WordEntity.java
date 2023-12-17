package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "words")
public class WordEntity extends BaseEntity {

    @Column(nullable = false)
    private String term;
    @Column(nullable = false)
    private String translation;
    @Column(columnDefinition = "TEXT")
    private String example;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private LanguageEntity language;
    @ManyToOne
    private UserEntity addedBy;

    public WordEntity() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public UserEntity getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserEntity addedBy) {
        this.addedBy = addedBy;
    }
}
