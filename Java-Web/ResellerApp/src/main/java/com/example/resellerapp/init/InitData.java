package com.example.resellerapp.init;

import com.example.resellerapp.model.entity.ConditionEntity;
import com.example.resellerapp.model.enums.ConditionNameEnum;
import com.example.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitData implements CommandLineRunner {

    private final ConditionRepository conditionRepository;

    public InitData(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (conditionRepository.count() == 0) {
            Arrays.stream(ConditionNameEnum.values())
                    .forEach(c -> {
                        ConditionEntity conditionEntity = new ConditionEntity();
                        conditionEntity.setName(c);
                        conditionRepository.save(conditionEntity);
                    });
        }
    }
}
