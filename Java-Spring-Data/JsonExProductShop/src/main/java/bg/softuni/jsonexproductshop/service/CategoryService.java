package bg.softuni.jsonexproductshop.service;

import bg.softuni.jsonexproductshop.model.dto.CategoryByProductCountDto;
import bg.softuni.jsonexproductshop.model.entity.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {

    void seedCategories() throws IOException;

    Set<Category> findRandomCategories();

    List<CategoryByProductCountDto> findCategoryByProductCount();
}
