package bg.softuni.automappingobjectsexercises.configs;

import bg.softuni.automappingobjectsexercises.models.dto.GameAddDto;
import bg.softuni.automappingobjectsexercises.models.dto.GameEditDto;
import bg.softuni.automappingobjectsexercises.models.dto.GameViewDto;
import bg.softuni.automappingobjectsexercises.models.entities.Game;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(GameAddDto.class, Game.class)
                .addMappings(mapper -> mapper.map(GameAddDto::getThumbnailUrl, Game::setImageThumbnail));

        modelMapper.typeMap(GameEditDto.class, Game.class)
                .addMappings(mapper -> mapper.map(GameEditDto::getThumbnailUrl, Game::setImageThumbnail));

        modelMapper.typeMap(Game.class, GameEditDto.class)
                .addMappings(mapper -> mapper.map(Game::getImageThumbnail, GameEditDto::setThumbnailUrl));

        modelMapper.typeMap(Game.class, GameViewDto.class)
                .addMappings(mapper -> mapper.map(Game::getImageThumbnail, GameViewDto::setThumbnailUrl));

        return modelMapper;
    }

}
