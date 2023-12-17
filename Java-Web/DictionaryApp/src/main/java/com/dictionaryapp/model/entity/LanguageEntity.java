package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageNameEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private LanguageNameEnum name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "language", fetch = FetchType.EAGER)
    private List<WordEntity> words;

    public LanguageEntity() {
        this.words = new ArrayList<>();
    }

    public LanguageNameEnum getName() {
        return name;
    }

    public void setName(LanguageNameEnum name) {
        this.name = name;
        this.descriptionInit(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WordEntity> getWords() {
        return words;
    }

    public void setWords(List<WordEntity> words) {
        this.words = words;
    }

    private void descriptionInit(LanguageNameEnum name) {
        switch (name) {
            case FRENCH -> setDescription("A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
            case GERMAN -> setDescription("A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
            case ITALIAN -> setDescription("A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
            case SPANISH -> setDescription("A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
        }
    }
}
