package bg.softuni.automappingobjectsexercises;

import bg.softuni.automappingobjectsexercises.common.ConstantMessages;
import bg.softuni.automappingobjectsexercises.models.dto.GameAddDto;
import bg.softuni.automappingobjectsexercises.models.dto.UserLoginDto;
import bg.softuni.automappingobjectsexercises.models.dto.UserRegistrationDto;
import bg.softuni.automappingobjectsexercises.services.GameService;
import bg.softuni.automappingobjectsexercises.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        String command = "";

        while (!"end".equalsIgnoreCase(command)) {
            System.out.println(ConstantMessages.ENTER_COMMAND);
            command = bufferedReader.readLine();
            String[] tokens = command.split("\\|");

            switch (tokens[0].toLowerCase()) {
                case "registeruser" -> userService
                        .registerUser(new UserRegistrationDto(tokens[1],
                                tokens[2], tokens[3], tokens[4]));
                case "loginuser" -> userService
                        .loginUser(new UserLoginDto(tokens[1], tokens[2]));
                case "logout" -> userService
                        .logout();
                case "addgame" -> gameService
                        .addGame(new GameAddDto(tokens[1], new BigDecimal(tokens[2]),
                                Double.parseDouble(tokens[3]), tokens[4], tokens[5],
                                tokens[6],
                                LocalDate.parse(tokens[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                case "editgame" -> gameService
                        .editGame(tokens[1], tokens);
                case "deletegame" -> gameService
                        .deleteGame(tokens[1]);
                case "allgames" -> gameService
                        .getAllGames();
                case "detailgame" -> gameService
                        .detailGame(tokens[1]);
                case "ownedgames" -> userService
                        .getOwnedGames();
            }

        }
    }

}
