package bg.softuni.springdataadvancedqueryinglab;

import bg.softuni.springdataadvancedqueryinglab.entities.Size;
import bg.softuni.springdataadvancedqueryinglab.services.IngredientService;
import bg.softuni.springdataadvancedqueryinglab.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private ShampooService shampooService;
    private IngredientService ingredientService;

    public Runner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Select task number(1-11):");
        int taskNUmber = Integer.parseInt(reader.readLine());

        switch (taskNUmber) {
            case 1 -> printAllShampooWhitGivenSize(Size.MEDIUM);
            case 2 -> printShampooBySizeOrLabelId(Size.MEDIUM, 10L);
            case 3 -> printAllShampooMoreExpensiveThan(BigDecimal.valueOf(5L));
            case 4 -> printIngredientNameStartWith("M");
            case 5 -> printSelectedIngredient(List.of("Lavender", "Herbs", "Apple"));
            case 6 -> printAllShampooLessExpensiveThen(BigDecimal.valueOf(8.50));
            case 7 -> printAllShampooWithIngredient(List.of("Berry", "Mineral-Collagen"));
            case 8 -> printAllShampooByIngredientCount(2L);
            case 9 -> deleteIngredientByName(reader.readLine());
            case 10 -> updateIngredientsPrice(10);
            case 11 -> updateIngredientsPriceByName("Nettle", BigDecimal.valueOf(12.00));
            default -> System.out.println("No such task exist");
        }

    }

    private void updateIngredientsPriceByName(String name, BigDecimal newPrice) {
        ingredientService.updateIngredientsPriceByName(name, newPrice);
    }

    private void updateIngredientsPrice(int percent) {
        ingredientService.updatePrice(percent);
    }

    private void deleteIngredientByName(String name) {
        ingredientService.deleteIngredientByName(name);
    }

    private void printAllShampooByIngredientCount(long count) {
        shampooService.getAllShampooByIngredientCount(count)
                .forEach(System.out::println);
    }

    private void printAllShampooWithIngredient(List<String> ingredients) {
        shampooService.getAllShampooWithIngredients(ingredients)
                .forEach(System.out::println);
    }

    private void printAllShampooLessExpensiveThen(BigDecimal price) {
        System.out.println(shampooService.getAllShampooLessExpensiveThen(price));
    }

    private void printSelectedIngredient(List<String> ingredients) {
        ingredientService.getSelectedIngredient(ingredients)
                .forEach(System.out::println);
    }

    private void printIngredientNameStartWith(String letter) {
        ingredientService.getAllIngredientNameStartWith(letter)
                .forEach(System.out::println);
    }

    private void printAllShampooMoreExpensiveThan(BigDecimal price) {
        shampooService.getAllShampooMoreExpensiveThen(price)
                .forEach(System.out::println);
    }

    private void printShampooBySizeOrLabelId(Size size, long labelId) {
        shampooService.getAllShampooWithGivenSizeAndLabelId(size, labelId)
                .forEach(System.out::println);
    }

    private void printAllShampooWhitGivenSize(Size size) {
        shampooService.getAllShampooWithGivenSize(size)
                .forEach(System.out::println);
    }

}
