package bg.softuni.jsonexproductshop;

import bg.softuni.jsonexproductshop.model.dto.CategoryByProductCountDto;
import bg.softuni.jsonexproductshop.model.dto.ProductNamePriceSellerDto;
import bg.softuni.jsonexproductshop.model.dto.UserSoldDto;
import bg.softuni.jsonexproductshop.service.CategoryService;
import bg.softuni.jsonexproductshop.service.ProductService;
import bg.softuni.jsonexproductshop.service.UserService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static bg.softuni.jsonexproductshop.common.ConstantFilePath.*;
import static bg.softuni.jsonexproductshop.common.ConstantMessages.*;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final Gson gson;
    private BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println(SELECT_TASK_NUMBER);
        System.out.println(TASK_LIST);

        int taskNum = Integer.parseInt(bufferedReader.readLine());

        switch (taskNum) {
            case 1 -> productInRange();
            case 2 -> successfullySoldProducts();
            case 3 -> categoryByProductsCount();
            case 4 -> usersAndProducts();
            default -> System.out.println(ERROR_TASK_NUMBER);
        }


    }

    private void usersAndProducts() {

    }

    private void categoryByProductsCount() throws IOException {
        List<CategoryByProductCountDto> categoryByProductCountDtos =
                categoryService.findCategoryByProductCount();

        String categoryContent = gson.toJson(categoryByProductCountDtos);

        writeToFIle(FILE_OUTPUT_PATH + CATEGORY_BY_PRODUCTS_COUNT__FILE_NAME, categoryContent);
    }

    private void successfullySoldProducts() throws IOException {
        List<UserSoldDto> userSoldDtos = userService
                .findAllWithSoldItemsWithBuyer();

        String userContent = gson.toJson(userSoldDtos);

        writeToFIle(FILE_OUTPUT_PATH + SUCCESSFULLY_SOLD_PRODUCT_FILE_NAME, userContent);
    }

    private void productInRange() throws IOException {
        List<ProductNamePriceSellerDto> productDtoList =
                productService.findAllProductInRangeWithNoBuyer(BigDecimal.valueOf(500),
                        BigDecimal.valueOf(1000));

        String productContent = gson.toJson(productDtoList);

        writeToFIle(FILE_OUTPUT_PATH + PRODUCT_IN_RANGE_FILE_NAME, productContent);
    }

    private void writeToFIle(String path, String content) throws IOException {
        Files.write(Path.of(path), Collections.singleton(content));
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        userService.seedUsers();
        productService.seedProducts();
    }

}
