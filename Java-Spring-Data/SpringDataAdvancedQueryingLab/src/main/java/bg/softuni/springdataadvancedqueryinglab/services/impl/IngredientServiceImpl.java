package bg.softuni.springdataadvancedqueryinglab.services.impl;

import bg.softuni.springdataadvancedqueryinglab.entities.Ingredient;
import bg.softuni.springdataadvancedqueryinglab.repositories.IngredientRepository;
import bg.softuni.springdataadvancedqueryinglab.services.IngredientService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> getAllIngredientNameStartWith(String letter) {
        return ingredientRepository.getAllByNameStartingWith(letter)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getSelectedIngredient(List<String> ingredients) {
        return ingredientRepository.getAllByNameIn(ingredients)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteIngredientByName(String name) {
        ingredientRepository.deleteByName(name);
    }

    @Override
    public void updatePrice(int percent) {
        ingredientRepository.updatePrice(percent);
    }

    @Override
    public void updateIngredientsPriceByName(String name, BigDecimal newPrice) {
        ingredientRepository.updatePriceByName(name, newPrice);
    }
}
