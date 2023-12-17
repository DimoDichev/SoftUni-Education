package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.WordAddDto;
import com.dictionaryapp.model.view.WordViewModel;

import java.util.List;

public interface WordService {
    void add(WordAddDto wordAddDto);

    List<WordViewModel> getAllWords();

    void remove(Long id);

    void removeAll();
}
