package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> getAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowPrice, BigDecimal highPrice);

    List<Book> getAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate releaseDate, LocalDate releaseDate2);

    List<Book> getAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> getAllByTitleContainsIgnoreCase(String string);

    @Query("SELECT b FROM Book b WHERE b.author.lastName LIKE CONCAT(:string, '%')")
    List<Book> getAllBooksByAuthorLastNameBeginWith(String string);

    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :length ")
    Integer countBookByTitleLength(int length);

    Book getAllByTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.copies = b.copies + :copies WHERE b.releaseDate > :releaseDate")
    Integer updateBooksCopies(LocalDate releaseDate, int copies);

    @Modifying
    @Transactional
    Integer removeBookByCopiesLessThan(Integer copies);

}
