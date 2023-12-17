package com.example.springintro;

import com.example.springintro.common.ConstantMessages;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println(ConstantMessages.SELECT_TASK);
        System.out.println(ConstantMessages.TASK_LIST);

        int taskNumber = Integer.parseInt(bufferedReader.readLine());

        switch (taskNumber) {
            case 1 -> bookTitlesByAgeRestriction();
            case 2 -> goldenBook();
            case 3 -> booksByPrice();
            case 4 -> notReleasedBooks();
            case 5 -> booksReleasedBeforeDate();
            case 6 -> authorSearch();
            case 7 -> booksSearch();
            case 8 -> bookTitleSearch();
            case 9 -> countBooks();
            case 10 -> totalBookCopies();
            case 11 -> reducedBook();
            case 12 -> increaseBookCopies();
            case 13 -> removeBooksWhichCopiesAreLowerThan();
        }

    }

    private void removeBooksWhichCopiesAreLowerThan() throws IOException {
        System.out.println(ConstantMessages.ENTER_COPIES_NUMBER);
        int copies = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Removed books " + bookService.removeBooksWhichLessCopiesThan(copies));
    }

    private void increaseBookCopies() throws IOException {
        System.out.println(ConstantMessages.ENTER_DATA_AND_COPIES);
        String date = bufferedReader.readLine();
        int copies = Integer.parseInt(bufferedReader.readLine());
        LocalDate releaseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MM yyyy"));

        System.out.println(bookService
                .increaseBookCopiesAfterDate(releaseDate, copies));
    }

    private void reducedBook() throws IOException {
        System.out.println(ConstantMessages.ENTER_BOOK_TITLE);
        String title = bufferedReader.readLine();
        System.out.println(bookService.getDataWithTitle(title));
    }

    private void totalBookCopies() {
        authorService.getAllAuthorWithTotalCopiesOfBooks()
                .forEach(System.out::println);
    }

    private void countBooks() throws IOException {
        System.out.println(ConstantMessages.ENTER_TITLE_LENGTH);
        int length = Integer.parseInt(bufferedReader.readLine());
        System.out.println(bookService.getBooksCountWitchTitleIsLongerThen(length));
    }

    private void bookTitleSearch() throws IOException {
        System.out.println(ConstantMessages.ENTER_BEGIN_STRING_OF_LAST_NAME);
        String string = bufferedReader.readLine();
        bookService.getAllBooksWitchAuthorLastNameStartWith(string)
                .forEach(System.out::println);
    }

    private void booksSearch() throws IOException {
        System.out.println(ConstantMessages.ENTER_STRING_CONTAINING_IN_TITLE);
        String string = bufferedReader.readLine();
        bookService.getAllBooksContainsInTitle(string)
                .forEach(System.out::println);
    }

    private void authorSearch() throws IOException {
        System.out.println(ConstantMessages.ENTER_END_STRING);
        String endString = bufferedReader.readLine();
        authorService.getAuthorByFirstNameEndingWith(endString)
                .forEach(System.out::println);
    }

    private void booksReleasedBeforeDate() throws IOException {
        System.out.println(ConstantMessages.ENTER_DATE);
        String date = bufferedReader.readLine();
        bookService.getAllBooksReleasedBefore(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .forEach(System.out::println);
    }

    private void notReleasedBooks() throws IOException {
        System.out.println(ConstantMessages.NOT_RELEASED_BOOK_YEAR);
        int year = Integer.parseInt(bufferedReader.readLine());
        bookService.getAllBooksThatAreNotReleasedIn(year)
                .forEach(System.out::println);
    }

    private void booksByPrice() {
        bookService.getAllBooksByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40))
                .forEach(System.out::println);
    }

    private void goldenBook() {
        bookService.getAllBooksByEditionTypeWithCopiesLessThan(EditionType.GOLD, 5000)
                .forEach(System.out::println);
    }

    private void bookTitlesByAgeRestriction() throws IOException {
        System.out.println(ConstantMessages.ENTER_AGE_RESTRICTION);
        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());
        bookService.getAllBooksByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
