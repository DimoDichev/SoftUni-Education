package bg.softuni.jsonexproductshop.repository;

import bg.softuni.jsonexproductshop.model.dto.CategoryByProductCountDto;
import bg.softuni.jsonexproductshop.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new bg.softuni.jsonexproductshop.model.dto.CategoryByProductCountDto(c.name, COUNT(p.id), AVG(p.price), SUM(p.price)) " +
            "FROM Product p JOIN p.categories c GROUP BY c.id " +
            "ORDER BY COUNT(p.id)" )
    List<CategoryByProductCountDto> findCategoryByProductCount();

}
