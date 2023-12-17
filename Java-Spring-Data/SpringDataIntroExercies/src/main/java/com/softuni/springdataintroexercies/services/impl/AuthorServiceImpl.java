package com.softuni.springdataintroexercies.services.impl;

import com.softuni.springdataintroexercies.models.entities.Author;
import com.softuni.springdataintroexercies.repositories.AuthorRepository;
import com.softuni.springdataintroexercies.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<String> getAuthorsAndTheirBooksCount() {
        return authorRepository.getAllByBooksSizeDesc()
                .stream()
                .map(author -> String.format("%s %s %d",
                            author.getFirstName(),
                            author.getLastName(),
                            author.getBooks().size()))
                .collect(Collectors.toList());
    }
}

