package bg.softuni.jsonexproductshop.service;

import bg.softuni.jsonexproductshop.model.dto.ProductNamePriceSellerDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts() throws IOException;

    List<ProductNamePriceSellerDto> findAllProductInRangeWithNoBuyer(BigDecimal lower, BigDecimal higher);

}
