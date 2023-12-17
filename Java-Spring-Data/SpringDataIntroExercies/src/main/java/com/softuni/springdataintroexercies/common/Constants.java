package com.softuni.springdataintroexercies.common;

public class Constants {

    public static final String AUTHORS_PATH = "src\\main\\resources\\files\\authors.txt";
    public static final String BOOKS_PATH = "src\\main\\resources\\files\\books.txt";
    public static final String CATEGORIES_PATH = "src\\main\\resources\\files\\categories.txt";
    public static final String SELECT_TASK = "Please select task number";
    public static final String TASK_LIST = String.format("1.Get all books after the year 2000%n" +
            "2.Get all authors with at least one book with a release date before 1990%n" +
            "3.Get all authors, ordered by the number of their books(descending)%n" +
            "4.Get all books from author George Powell");
    public static final String NO_SUCH_TASK = "No such task";

}
