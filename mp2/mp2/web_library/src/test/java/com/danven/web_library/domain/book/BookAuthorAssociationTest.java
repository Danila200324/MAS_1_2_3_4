package com.danven.web_library.domain.book;

import com.danven.web_library.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookAuthorAssociationTest {

    private Book book_first;
    private Book book_second;
    private Author author_first;

    @BeforeEach
    public void setup() {
        book_first = Factory.getDBookWithAuthorAndDefaultParameters("Book 1");
        book_second = Factory.getPBookWithAuthorAndDefaultParameters("Book 2");
        author_first = Factory.getAuthorByFullName("Author 1");
    }

    @Test
    public void testCreateAssociationBetweenBookAndAuthor() {
        // Before
        assertEquals(0, book_first.getBookAuthors().size(), "Book should initially have no book authors");
        assertEquals(0, author_first.getBookAuthors().size(), "Book should initially have no book authors");

        // Create and add a new book-author association
        BookAuthor bookAuthor = new BookAuthor.BookAuthorBuilder()
                .setBook(book_first)
                .setAuthor(author_first)
                .setRole("Writer")
                .build();

        // After
        assertEquals(1, book_first.getBookAuthors().size(), "Book should contain 1 author association after addition");
        assertTrue(book_first.getBookAuthors().contains(bookAuthor), "Book should contain the specific author association");

        assertEquals(1, author_first.getBookAuthors().size(), "Author should contain 1 book association after addition");
        assertTrue(author_first.getBookAuthors().contains(bookAuthor), "Author should contain the specific book association");

        assertEquals(book_first, bookAuthor.getBook(), "Association's book reference should point to the correct book");
        assertEquals(author_first, bookAuthor.getAuthor(), "Association's author reference should point to the correct author");
    }

    @Test
    public void testRemoveAssociationBetweenBookAndAuthorFromBook() {

        // Init
        BookAuthor bookAuthor = new BookAuthor.BookAuthorBuilder()
                .setBook(book_first)
                .setAuthor(author_first)
                .setRole("Writer")
                .build();

        // Before
        assertEquals(1, book_first.getBookAuthors().size(), "Book should contain 1 author before removal");
        assertEquals(1, author_first.getBookAuthors().size(), "Author should contain 1 book before removal");

        // Operation
        book_first.deleteAuthorBook(bookAuthor);

        // After
        assertEquals(0, book_first.getBookAuthors().size(), "Book should have no authors after removal");
        assertEquals(0, author_first.getBookAuthors().size(), "Author should have no books after removal");
    }

    @Test
    public void testRemoveAssociationBetweenBookAndAuthorFromBookAuthor() {

        // Init
        BookAuthor bookAuthor = new BookAuthor.BookAuthorBuilder()
                .setBook(book_first)
                .setAuthor(author_first)
                .setRole("Writer")
                .build();

        // Before
        assertEquals(1, book_first.getBookAuthors().size(), "Book should contain 1 author before removal");
        assertEquals(1, author_first.getBookAuthors().size(), "Author should contain 1 book before removal");

        // Operation
        bookAuthor.deleteBookAuthor();

        // After
        assertEquals(0, book_first.getBookAuthors().size(), "Book should have no authors after removal");
        assertEquals(0, author_first.getBookAuthors().size(), "Author should have no books after removal");

        assertNull(bookAuthor.getAuthor());
        assertNull(bookAuthor.getBook());
    }

    @Test
    public void testRemoveAssociationBetweenBookAndAuthorFromAuthor() {

        // Init
        BookAuthor bookAuthor = new BookAuthor.BookAuthorBuilder()
                .setBook(book_first)
                .setAuthor(author_first)
                .setRole("Writer")
                .build();

        // Before
        assertEquals(1, book_first.getBookAuthors().size(), "Book should contain 1 author before removal");
        assertEquals(1, author_first.getBookAuthors().size(), "Author should contain 1 book before removal");

        // Operation
        author_first.deleteAuthorBook(bookAuthor);

        // After
        assertEquals(0, book_first.getBookAuthors().size(), "Book should have no authors after removal");
        assertEquals(0, author_first.getBookAuthors().size(), "Author should have no books after removal");
    }

    @Test
    public void testChangeAuthorRole() {
        // Setup
        BookAuthor bookAuthor = new BookAuthor.BookAuthorBuilder()
                .setBook(book_first)
                .setAuthor(author_first)
                .setRole("Writer")
                .build();

        // Before
        assertEquals("Writer", bookAuthor.getRole(), "Initial role should be 'Writer'");

        // Update role (assuming there is a setRole method or similar functionality)
        bookAuthor.setRole("Editor");

        // After
        assertEquals("Editor", bookAuthor.getRole(), "Role should be updated to 'Editor'");
    }

}
