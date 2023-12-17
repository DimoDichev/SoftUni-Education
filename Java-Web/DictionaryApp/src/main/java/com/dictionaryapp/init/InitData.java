package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitData implements CommandLineRunner {

    private final LanguageRepository languageRepository;

    public InitData(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (languageRepository.count() == 0) {
            Arrays.stream(LanguageNameEnum.values())
                    .forEach(l -> {
                        LanguageEntity language = new LanguageEntity();
                        language.setName(l);
                        languageRepository.save(language);
                    });
        }
    }
}
