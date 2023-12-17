package bg.softuni.springdataadvancedqueryinglab.services.impl;

import bg.softuni.springdataadvancedqueryinglab.entities.Shampoo;
import bg.softuni.springdataadvancedqueryinglab.entities.Size;
import bg.softuni.springdataadvancedqueryinglab.repositories.ShampooRepository;
import bg.softuni.springdataadvancedqueryinglab.services.ShampooService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {

    private ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> getAllShampooWithGivenSize(Size size) {
        return shampooRepository.getAllBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> getAllShampooWithGivenSizeAndLabelId(Size size, long labelId) {
        return shampooRepository.getAllBySizeOrLabel_IdOrderByPrice(size, labelId);
    }

    @Override
    public List<Shampoo> getAllShampooMoreExpensiveThen(BigDecimal price) {
        return shampooRepository.getAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public Integer getAllShampooLessExpensiveThen(BigDecimal price) {
        return shampooRepository.countByPriceLessThan(price);
    }

    @Override
    public List<String> getAllShampooWithIngredients(List<String> ingredients) {
        return shampooRepository.getAllByIngredientsContaining(ingredients)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampooByIngredientCount(long count) {
        return shampooRepository.getAllByIngredientsCount(count)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

}
