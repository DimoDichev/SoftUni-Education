package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.WordAddDto;
import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.view.WordViewModel;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final LoggedUser loggedUser;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;

    public WordServiceImpl(WordRepository wordRepository, LoggedUser loggedUser, LanguageRepository languageRepository, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.loggedUser = loggedUser;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(WordAddDto wordAddDto) {
        LanguageEntity language = languageRepository.findByName(wordAddDto.getLanguage()).orElse(null);
        UserEntity user = userRepository.findByUsername(loggedUser.getUsername()).orElse(null);

        WordEntity word = new WordEntity();
        word.setTerm(wordAddDto.getTerm());
        word.setTranslation(wordAddDto.getTranslation());
        word.setExample(wordAddDto.getExample());
        word.setDate(wordAddDto.getInputDate());
        word.setLanguage(language);
        word.setAddedBy(user);

        wordRepository.save(word);
    }

    @Override
    public List<WordViewModel> getAllWords() {
       return wordRepository.findAll()
                .stream()
                .map(w -> {
                    UserEntity user = userRepository.findById(w.getAddedBy().getId()).orElse(null);

                    WordViewModel word = new WordViewModel();
                    word.setId(w.getId());
                    word.setTerm(w.getTerm());
                    word.setTranslation(w.getTranslation());
                    word.setExample(w.getExample());
                    word.setDate(w.getDate());
                    word.setLanguage(w.getLanguage().getName().name());
                    word.setUsername(user == null ? "" : user.getUsername());
                    return word;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        wordRepository.deleteAll();
    }
}
