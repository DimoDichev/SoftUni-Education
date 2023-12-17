package com.softuni.springdataintroexercies.services.impl;

import com.softuni.springdataintroexercies.common.Constants;
import com.softuni.springdataintroexercies.models.entities.Author;
import com.softuni.springdataintroexercies.models.entities.Book;
import com.softuni.springdataintroexercies.models.entities.Category;
import com.softuni.springdataintroexercies.models.enums.AgeRestriction;
import com.softuni.springdataintroexercies.models.enums.EditionType;
import com.softuni.springdataintroexercies.repositories.AuthorRepository;
import com.softuni.springdataintroexercies.repositories.BookRepository;
import com.softuni.springdataintroexercies.repositories.CategoryRepository;
import com.softuni.springdataintroexercies.services.SeedService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public SeedServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() > 0) return;

        Files.readAllLines(Path.of(Constants.AUTHORS_PATH))
                .forEach(row -> {
                    String[] data = row.split("\\s+");
                    Author author = new Author(data[0], data[1]);
                    authorRepository.save(author);
                });
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() > 0) return;

        Files.readAllLines(Path.of(Constants.BOOKS_PATH))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Book book = createBookFromData(data);

                    bookRepository.save(book);
                });
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() > 0) return;

        Files.readAllLines(Path.of(Constants.CATEGORIES_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(categoryName -> {
                    Category category = new Category(categoryName);

                    categoryRepository.save(category);
                });
    }

    private Book createBookFromData(String[] data) {
        EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
        LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        int copies = Integer.parseInt(data[2]);
        BigDecimal price = new BigDecimal(data[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
        String title = Arrays.stream(data)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = getRandomAuthor();
        Set<Category> categories = getRandomCategories();

        return new Book(title, editionType, price, copies, releaseDate, ageRestriction, author, categories);
    }

    private Author getRandomAuthor() {
        long randomId = ThreadLocalRandom.current()
                .nextLong(1, authorRepository.count() + 1);
        return authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randomCount = ThreadLocalRandom.current().nextInt(1, 2);

        for (int i = 0; i < randomCount; i++) {
            long randomId = ThreadLocalRandom.current()
                    .nextLong(1, categoryRepository.count() + 1);

            Category category = categoryRepository.findById(randomId).orElse(null);

            categories.add(category);
        }

        return categories;
    }

}
