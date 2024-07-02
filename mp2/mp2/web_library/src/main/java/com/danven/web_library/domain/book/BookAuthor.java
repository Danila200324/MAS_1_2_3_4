package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookAuthor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final List<BookAuthor> records = new ArrayList<>();

    private Book book;
    private Author author;
    private String role;

    private BookAuthor(BookAuthorBuilder bookAuthorBuilder) {

        validateBook(bookAuthorBuilder.book);
        validateAuthor(bookAuthorBuilder.author);
        validateRole(bookAuthorBuilder.role);

        this.book = bookAuthorBuilder.book;
        this.author = bookAuthorBuilder.author;
        this.role = bookAuthorBuilder.role;

        book.addAuthorBook(this);
        author.addAuthorBook(this);

        records.add(this);
    }

    public static class BookAuthorBuilder {

        private Book book;

        private Author author;

        private String role;

        public BookAuthor.BookAuthorBuilder setBook(Book book) {
            this.book = book;
            return this;
        }

        public BookAuthor.BookAuthorBuilder setAuthor(Author author) {
            this.author = author;
            return this;
        }

        public BookAuthor.BookAuthorBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public BookAuthor build() {
            return new BookAuthor(this);
        }
    }


    void deleteBookAuthor() {
        records.remove(this);
        this.book.cleanAuthorBookFromSet(this);
        this.author.cleanAuthorBookFromSet(this);
        this.book = null;
        this.author = null;
    }

    private void validateRole(String role) {
        if (role == null || role.isBlank()) {
            throw new ValidationException("Role can't be null");
        }
    }

    private void validateBook(Book book) {
        if (book == null) {
            throw new ValidationException("Book can't me empty");
        }
    }

    private void validateAuthor(Author author) {
        if (author == null) {
            throw new ValidationException("Author can't me empty");
        }
    }

    public void setRole(String role) {
        validateRole(role);
        this.role = role;
    }

    public Book getBook() {
        return book;
    }

    public Author getAuthor() {
        return author;
    }

    public String getRole() {
        return role;
    }

}
