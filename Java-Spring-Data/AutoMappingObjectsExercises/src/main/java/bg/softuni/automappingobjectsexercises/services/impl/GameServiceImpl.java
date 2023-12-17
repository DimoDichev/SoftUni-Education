package bg.softuni.automappingobjectsexercises.services.impl;

import bg.softuni.automappingobjectsexercises.common.ConstantMessages;
import bg.softuni.automappingobjectsexercises.models.dto.GameAddDto;
import bg.softuni.automappingobjectsexercises.models.dto.GameEditDto;
import bg.softuni.automappingobjectsexercises.models.dto.GameViewDto;
import bg.softuni.automappingobjectsexercises.models.entities.Game;
import bg.softuni.automappingobjectsexercises.repositories.GameRepository;
import bg.softuni.automappingobjectsexercises.services.GameService;
import bg.softuni.automappingobjectsexercises.services.UserService;
import bg.softuni.automappingobjectsexercises.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private UserService userService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    @Override
    public void addGame(GameAddDto gameToAdd) {

        if (checkAdminLogin()) return;

        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.violations(gameToAdd);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        Game game = modelMapper.map(gameToAdd, Game.class);
        gameRepository.save(game);
        System.out.printf(ConstantMessages.GAME_ADDED_SUCCESSFULLY, game.getTitle());
    }

    @Override
    public void deleteGame(String gameId) {
        if (checkAdminLogin()) return;

        Long id = Long.parseLong(gameId);

        Optional<Game> gameOpt = gameRepository.findById(id);

        if (gameOpt.isEmpty()) {
            System.out.println(ConstantMessages.INVALID_GAME_ID);
            return;
        }

        Game game = gameOpt.get();

        gameRepository.delete(game);
        System.out.printf(ConstantMessages.DELETE_GAME_SUCCESSFUL, game.getTitle());
    }

    @Override
    public void editGame(String gameId, String[] values) {
        if (checkAdminLogin()) return;

        Long id = Long.parseLong(gameId);

        Optional<Game> gameToEdit = gameRepository.findById(id);

        if (gameToEdit.isEmpty()) {
            System.out.println(ConstantMessages.INVALID_GAME_ID);
            return;
        }

        Map<String, String> updateArgs = new HashMap<>();

        for (int i = 2; i < values.length; i++) {
            String[] argsList = values[i].split("=");
            updateArgs.put(argsList[0], argsList[1]);
        }

        GameEditDto editedGameDto = this.modelMapper.map(gameToEdit.get(), GameEditDto.class);
        editedGameDto.updateField(updateArgs);

        Game game = this.modelMapper.map(editedGameDto, Game.class);

        gameRepository.save(game);
        System.out.printf(ConstantMessages.EDITED_SUCCESSFUL, game.getTitle());
    }

    @Override
    public void getAllGames() {
        List<Game> allGames = gameRepository.findAll();

        allGames.stream()
                .map(g -> this.modelMapper.map(g, GameViewDto.class))
                .forEach(System.out::println);
    }

    @Override
    public void detailGame(String gameName) {
        Optional<Game> gameOpt = gameRepository.findByTitle(gameName);

        if (gameOpt.isEmpty()) {
            System.out.println(ConstantMessages.INCORRECT_TITLE_INSERT);
            return;
        }

        GameViewDto game = modelMapper.map(gameOpt.get(), GameViewDto.class);

        System.out.println(game.allInfo());

    }

    private boolean checkAdminLogin() {
        if (!userService.hasLoggedInUser()) {
            System.out.println(ConstantMessages.NO_LOGGED_IN_USER);
            return true;
        }

        if (!userService.hasUserAreAdministrator()) {
            System.out.println(ConstantMessages.NO_ADMINISTRATOR_LOGGED);
            return true;
        }
        return false;
    }

}
