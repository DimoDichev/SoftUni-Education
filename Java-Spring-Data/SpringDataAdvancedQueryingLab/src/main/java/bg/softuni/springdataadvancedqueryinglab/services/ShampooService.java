package bg.softuni.springdataadvancedqueryinglab.services;

import bg.softuni.springdataadvancedqueryinglab.entities.Shampoo;
import bg.softuni.springdataadvancedqueryinglab.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<Shampoo> getAllShampooWithGivenSize(Size size);

    List<Shampoo> getAllShampooWithGivenSizeAndLabelId(Size size, long labelId);

    List<Shampoo> getAllShampooMoreExpensiveThen(BigDecimal price);

    Integer getAllShampooLessExpensiveThen(BigDecimal price);

    List<String> getAllShampooWithIngredients(List<String> ingredients);

    List<String> getAllShampooByIngredientCount(long count);
}
