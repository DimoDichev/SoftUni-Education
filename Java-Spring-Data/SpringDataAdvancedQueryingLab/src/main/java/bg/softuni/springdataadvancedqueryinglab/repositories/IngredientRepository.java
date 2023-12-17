package bg.softuni.springdataadvancedqueryinglab.repositories;

import bg.softuni.springdataadvancedqueryinglab.entities.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> getAllByNameStartingWith(String letter);
    List<Ingredient> getAllByNameIn(List<String> ingredients);

    @Modifying
    @Transactional
    void deleteByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient i " +
            "SET i.price = i.price * (1 + (:percent / 100))")
    void updatePrice(int percent);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient  i " +
            "SET i.price = :price " +
            "WHERE i.name = :name")
    void updatePriceByName(String name, BigDecimal price);

}
