package com.danven.web_library.book;

import com.danven.web_library.domain.book.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookAuthorTest {

    private Book book;
    private Author author;

    @BeforeEach
    public void setup() {
        Category cat1, cat3;
        cat1 = new Category("Fiction", "Fictional works");
        cat3 = new Category("Drama", "Drama book");
        Set<Category> categories = new HashSet<>();
        categories.add(cat1);
        categories.add(cat3);

        Set<Category> primarilyCategories = new HashSet<>();
        primarilyCategories.add(cat1);
        book = new PBook("Example Book", 2021, "An example description",
                categories, primarilyCategories, "English", 1, 560);
        author = new Author("John Doe");
    }

    @Test
    public void testAddingDuplicateBookAuthorAssociations() {
        BookAuthor ba1 = new BookAuthor(book, author, "Writer");
        BookAuthor ba2 = new BookAuthor(book, author, "Writer");

        Assertions.assertEquals(2, book.getBookAuthors().size());
        Assertions.assertEquals(2, book.getBookAuthors().size());
    }

    @Test
    public void testRemovingAssociations() {
        BookAuthor ba1 = new BookAuthor(book, author, "Writer");
        BookAuthor ba2 = new BookAuthor(book, author, "Writer");

        book.deleteAuthorBook(ba1);

        Assertions.assertEquals(1, book.getBookAuthors().size());
        Assertions.assertEquals(1, author.getBookAuthors().size());
    }

    @Test
    public void testRetrievingDuplicateAssociations() {
        new BookAuthor(book, author, "Writer");
        new BookAuthor(book, author, "Writer");

        List<BookAuthor> authorAssociations = author.getBookAuthors();
        Assertions.assertEquals(2, authorAssociations.size());
    }
}

