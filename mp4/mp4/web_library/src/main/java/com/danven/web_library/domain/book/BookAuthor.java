package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookAuthor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final List<BookAuthor> records = new ArrayList<>();

    private Book book;
    private Author author;
    private String role;

    public BookAuthor(Book book, Author author, String role) {

        validateBook(book);
        validateAuthor(author);
        validateRole(role);

        this.book = book;
        this.author = author;
        this.role = role;

        book.addAuthorBook(this);
        author.addAuthorBook(this);

        records.add(this);
    }


    public void removeBookAuthor() {
        records.remove(this);
        if(this.book != null){
            book.deleteAuthorBook(this);
        }
        if(this.author != null){
            author.deleteAuthorBook(this);
        }
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

    public static List<BookAuthor> getRecords(){
        return Collections.unmodifiableList(records);
    }

}

