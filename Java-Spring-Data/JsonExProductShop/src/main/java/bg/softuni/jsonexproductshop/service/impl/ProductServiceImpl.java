package bg.softuni.jsonexproductshop.service.impl;

import bg.softuni.jsonexproductshop.model.dto.ProductNamePriceSellerDto;
import bg.softuni.jsonexproductshop.model.dto.ProductSeedDto;
import bg.softuni.jsonexproductshop.model.entity.Product;
import bg.softuni.jsonexproductshop.model.entity.User;
import bg.softuni.jsonexproductshop.repository.ProductRepository;
import bg.softuni.jsonexproductshop.service.CategoryService;
import bg.softuni.jsonexproductshop.service.ProductService;
import bg.softuni.jsonexproductshop.service.UserService;
import bg.softuni.jsonexproductshop.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static bg.softuni.jsonexproductshop.common.ConstantFilePath.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, CategoryService categoryService, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() > 0) return;

        String fileContent = Files.readString(Path.of(FILE_READ_PATH + PRODUCT_PATH));

        ProductSeedDto[] productSeedDtos = gson.fromJson(fileContent, ProductSeedDto[].class);

        Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.findRandomUser());
                    product.setBuyer(setRandomBuyer());
                    product.setCategories(categoryService.findRandomCategories());
                    return product;
                })
                .forEach(productRepository::save);
    }

    @Override
    public List<ProductNamePriceSellerDto> findAllProductInRangeWithNoBuyer(BigDecimal lower, BigDecimal higher) {
        return productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lower, higher)
                .stream()
                .map(product -> {
                    ProductNamePriceSellerDto productNamePriceSellerDto =
                            modelMapper.map(product, ProductNamePriceSellerDto.class);

                    productNamePriceSellerDto.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));

                    return productNamePriceSellerDto;
                })
                .collect(Collectors.toList());
    }

    private User setRandomBuyer() {
        int optional = ThreadLocalRandom.current().nextInt(1, 3);

        if (optional == 1) {
            return userService.findRandomUser();
        }

        return null;
    }

}
