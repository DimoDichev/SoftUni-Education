package com.softuni.springdataintroexercies.services;

import com.softuni.springdataintroexercies.models.entities.Author;

import java.util.List;

public interface AuthorService {

    List<String> getAuthorsAndTheirBooksCount();

}
