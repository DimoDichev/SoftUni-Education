package bg.softuni.springdataadvancedqueryinglab.services;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    List<String> getAllIngredientNameStartWith(String letter);

    List<String> getSelectedIngredient(List<String> ingredients);

    void deleteIngredientByName(String name);

    void updatePrice(int percent);

    void updateIngredientsPriceByName(String name, BigDecimal newPrice);
}
