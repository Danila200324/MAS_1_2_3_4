package com.danven.web_library.service.book_service;

import com.danven.web_library.RecordsUtility;
import com.danven.web_library.domain.book.Book;
import com.danven.web_library.domain.book.Category;
import com.danven.web_library.domain.book.DiskBook;
import com.danven.web_library.domain.book.DiskFormat;
import com.danven.web_library.service.book_service.pagination.BookSimplePaginationStrategyService;
import com.danven.web_library.service.book_service.pagination.BookWindowPaginationStrategyService;
import com.danven.web_library.service.book_service.sorting.BookServiceSortByName;


import com.danven.web_library.service.book_service.sorting.BookServiceSortByYear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    private Book book1;

    private Book book2;

    private Book book3;

    private Book book4;

    @BeforeEach
    public void init() {

        RecordsUtility.clearRecords();

        Category category1 = new Category("Category 1", "Description for category 1");

        Category category2 = new Category("Category 2", "Description for category 2");

        Category category3 = new Category("Category 3", "Description for category 3");

        book1 = new DiskBook(
                "A",
                1990,
                "Description for Book 1",
                "Author for Book 1",
                "English",
                Set.of(category1, category2),
                3.90,
                DiskFormat.CD);

        book2 = new DiskBook(
                "B",
                2000,
                "Description for Book 2",
                "Author for Book 2",
                "Polish",
                Set.of(category3),
                4.90,
                DiskFormat.DVD);

        book3 = new DiskBook(
                "C",
                2002,
                "Description for Book 3",
                "Author for Book 3",
                "Germany",
                Set.of(category3, category2),
                1.90,
                DiskFormat.DVD);

        book4 = new DiskBook(
                "D",
                2003,
                "Description for Book 3",
                "Author for Book 3",
                "Germany",
                Set.of(category3, category2),
                1.90,
                DiskFormat.DVD);
    }

    @Test
    public void testBookSimplePaginationServiceWithSortingByName() {
        BookSimplePaginationStrategyService bookSimplePaginationStrategyService =
                new BookSimplePaginationStrategyService(new BookServiceSortByName(), 2);

        assertIterableEquals(List.of(book1, book2),
                bookSimplePaginationStrategyService.getBooks(0));

    }

    @Test
    public void testBookSimplePaginationServiceWithSortingByYear() {
        BookSimplePaginationStrategyService bookSimplePaginationStrategyService =
                new BookSimplePaginationStrategyService(new BookServiceSortByYear(), 2);

        assertIterableEquals(List.of(book1, book2),
                bookSimplePaginationStrategyService.getBooks(0));

    }

    @Test
    public void testBookWindowPaginationServiceWithSortingByYear() {
        BookWindowPaginationStrategyService bookSimplePaginationStrategyService =
                new BookWindowPaginationStrategyService(new BookServiceSortByYear(), 1);

        assertIterableEquals(List.of(book1),
                bookSimplePaginationStrategyService.getBooks(0));

    }

    @Test
    public void testBookWindowPaginationServiceWithSortingByName() {
        BookService bookSimplePaginationStrategyService =
                new BookWindowPaginationStrategyService(new BookServiceSortByName(), 2);

        assertIterableEquals(List.of(book1, book2, book3),
                bookSimplePaginationStrategyService.getBooks(0));

    }

}
