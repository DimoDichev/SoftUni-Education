package bg.softuni.jsonexproductshop.service.impl;

import bg.softuni.jsonexproductshop.model.dto.CategoryByProductCountDto;
import bg.softuni.jsonexproductshop.model.dto.CategorySeedDto;
import bg.softuni.jsonexproductshop.model.entity.Category;
import bg.softuni.jsonexproductshop.repository.CategoryRepository;
import bg.softuni.jsonexproductshop.service.CategoryService;
import bg.softuni.jsonexproductshop.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static bg.softuni.jsonexproductshop.common.ConstantFilePath.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0) return;

        String fileContent = Files.readString(Path.of(FILE_READ_PATH + CATEGORY_PATH));

        CategorySeedDto[] categorySeedDtos = gson.fromJson(fileContent, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public Set<Category> findRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int categoryCount = ThreadLocalRandom.current().nextInt(1, 3);
        long categoryRepoCount = categoryRepository.count();

        for (int i = 0; i < categoryCount; i++) {
            Long randomId = ThreadLocalRandom.current().nextLong(1, categoryRepoCount + 1);

            categories.add(categoryRepository.findById(randomId).orElse(null));
        }

        return categories;
    }

    @Override
    public List<CategoryByProductCountDto> findCategoryByProductCount() {
        return categoryRepository.findCategoryByProductCount();
    }

}
