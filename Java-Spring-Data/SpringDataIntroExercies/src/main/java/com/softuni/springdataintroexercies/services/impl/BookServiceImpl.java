package com.softuni.springdataintroexercies.services.impl;

import com.softuni.springdataintroexercies.models.entities.Book;
import com.softuni.springdataintroexercies.repositories.BookRepository;
import com.softuni.springdataintroexercies.services.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooksAfterYear(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateAfter(date);
    }

    @Override
    public List<Book> getAuthorsWithBookAfter(LocalDate date) {
        return bookRepository.findAllByReleaseDateBefore(date);
    }

    @Override
    public List<Book> getAllBooksFrom(String firstName, String lastName) {
        return bookRepository.findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(firstName, lastName);
    }

}
