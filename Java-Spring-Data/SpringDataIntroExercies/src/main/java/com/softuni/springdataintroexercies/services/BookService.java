package com.softuni.springdataintroexercies.services;

import com.softuni.springdataintroexercies.models.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    List<Book> getBooksAfterYear(LocalDate date);

    List<Book> getAuthorsWithBookAfter(LocalDate date);

    List<Book> getAllBooksFrom(String firstName, String lastName);
}
