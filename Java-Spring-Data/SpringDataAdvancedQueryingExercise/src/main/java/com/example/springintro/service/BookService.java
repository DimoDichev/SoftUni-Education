package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> getAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<String> getAllBooksByEditionTypeWithCopiesLessThan(EditionType editionType, Integer copies);

    List<String> getAllBooksByPriceLessThanOrPriceGreaterThan(BigDecimal lowPrice, BigDecimal highPrice);

    List<String> getAllBooksThatAreNotReleasedIn(int year);

    List<String> getAllBooksReleasedBefore(LocalDate localDate);

    List<String> getAllBooksContainsInTitle(String string);

    List<String> getAllBooksWitchAuthorLastNameStartWith(String string);

    Integer getBooksCountWitchTitleIsLongerThen(int length);

    String getDataWithTitle(String title);

    Integer increaseBookCopiesAfterDate(LocalDate releaseDate, int copies);

    Integer removeBooksWhichLessCopiesThan(int copies);
}
