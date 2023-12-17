package bg.softuni.automappingobjectsexercises.services;

import bg.softuni.automappingobjectsexercises.models.dto.GameAddDto;

import java.util.List;
import java.util.stream.Stream;

public interface GameService {

    void addGame(GameAddDto gameAddDto);

    void deleteGame(String token);

    void editGame(String gameId, String[] values);

    void getAllGames();

    void detailGame(String token);
}
