package com.softuni.springdataintroexercies;

import com.softuni.springdataintroexercies.common.Constants;
import com.softuni.springdataintroexercies.services.AuthorService;
import com.softuni.springdataintroexercies.services.BookService;
import com.softuni.springdataintroexercies.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final SeedService seedService;
    private final AuthorService authorService;
    private final BookService bookService;

    public ConsoleRunner(SeedService seedService, AuthorService authorService, BookService bookService) {
        this.seedService = seedService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedAllData();

        System.out.println(Constants.SELECT_TASK);
        System.out.println(Constants.TASK_LIST);

        int taskNumber = Integer.parseInt(reader.readLine());

        switch (taskNumber) {
            case 1 -> printBookAfter2000();
            case 2 -> printAuthorsWithBookBefore1990();
            case 3 -> printAllAuthorsAndTheirBooks();
            case 4 -> printAllBooksFromAuthor();
            default -> System.out.println(Constants.NO_SUCH_TASK);
        }
    }

    private void printAllAuthorsAndTheirBooks() {
        authorService.getAuthorsAndTheirBooksCount()
                .forEach(System.out::println);
    }

    private void printAllBooksFromAuthor() {
        bookService.getAllBooksFrom("George", "Powell")
                .forEach(book -> System.out.printf("%s %s %d%n",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()));
    }

    private void printAuthorsWithBookBefore1990() {
        bookService.getAuthorsWithBookAfter(LocalDate.of(1990, 1, 1))
                .forEach(book -> System.out.printf("%s %s%n",
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()));
    }

    private void printBookAfter2000() {
        bookService.getBooksAfterYear(LocalDate.of(1999, 12, 31))
                .forEach(book -> System.out.println(book.getTitle()));
    }

}
