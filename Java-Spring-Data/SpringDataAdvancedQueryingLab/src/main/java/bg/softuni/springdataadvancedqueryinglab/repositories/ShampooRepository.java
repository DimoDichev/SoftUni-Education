package bg.softuni.springdataadvancedqueryinglab.repositories;

import bg.softuni.springdataadvancedqueryinglab.entities.Shampoo;
import bg.softuni.springdataadvancedqueryinglab.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> getAllBySizeOrderById(Size size);
    List<Shampoo> getAllBySizeOrLabel_IdOrderByPrice(Size size, Long labelId);
    List<Shampoo> getAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    Integer countByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo AS s " +
            "INNER JOIN s.ingredients AS i " +
            "WHERE i.name IN :ingredients")
    List<Shampoo> getAllByIngredientsContaining(List<String> ingredients);

    @Query("SELECT s FROM Shampoo s " +
            "JOIN s.ingredients i " +
            "GROUP BY s.id " +
            "HAVING COUNT(i) < :count")
    List<Shampoo> getAllByIngredientsCount(long count);
}
