package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public Integer removeBooksWhichLessCopiesThan(int copies) {
        return bookRepository.removeBookByCopiesLessThan(copies);
    }

    @Override
    public Integer increaseBookCopiesAfterDate(LocalDate releaseDate, int copies) {
        return bookRepository.updateBooksCopies(releaseDate, copies) * copies;
    }

    @Override
    public String getDataWithTitle(String title) {
        Book book = bookRepository.getAllByTitle(title);
        return String.format("%s %s %s %.2f",
                book.getTitle(),
                book.getEditionType().name(),
                book.getAgeRestriction().name(),
                book.getPrice());
    }

    @Override
    public Integer getBooksCountWitchTitleIsLongerThen(int length) {
        return bookRepository.countBookByTitleLength(length);
    }

    @Override
    public List<String> getAllBooksWitchAuthorLastNameStartWith(String string) {
        return bookRepository.getAllBooksByAuthorLastNameBeginWith(string)
                .stream()
                .map(book -> String.format("%s (%s %s)",
                        book.getTitle(),
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksContainsInTitle(String string) {
        return bookRepository.getAllByTitleContainsIgnoreCase(string)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksReleasedBefore(LocalDate localDate) {
        return bookRepository.getAllByReleaseDateBefore(localDate)
                .stream()
                .map(book -> String.format("%s %s %.2f",
                        book.getTitle(),
                        book.getEditionType().name(),
                        book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksByPriceLessThanOrPriceGreaterThan(BigDecimal lowPrice, BigDecimal highPrice) {
        return bookRepository.getAllByPriceLessThanOrPriceGreaterThan(lowPrice, highPrice)
                .stream()
                .map(book -> String.format("%s - $%.2f",
                        book.getTitle(),
                        book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksThatAreNotReleasedIn(int year) {
        LocalDate lower = LocalDate.of(year, 1, 1);
        LocalDate higher = LocalDate.of(year, 12, 31);

        return bookRepository.getAllByReleaseDateBeforeOrReleaseDateAfter(lower, higher)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksByEditionTypeWithCopiesLessThan(EditionType editionType, Integer copies) {
        return bookRepository.getAllByEditionTypeAndCopiesLessThan(editionType, copies)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.getAllByAgeRestriction(ageRestriction)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
