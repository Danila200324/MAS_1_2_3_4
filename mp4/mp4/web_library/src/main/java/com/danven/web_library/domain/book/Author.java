package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Author implements Serializable {

    @Serial
    private static final long serialVersionUID = 56467L;

    private String fullName;

    private final List<BookAuthor> bookAuthors = new ArrayList<>();

    public Author(String fullName) {
        validateFullName(fullName);
        this.fullName = fullName;
    }

    public void validateFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new ValidationException("FullName can't be blank");
        }
    }

    public void addAuthorBook(BookAuthor bookAuthor) {
        validateBookAuthor(bookAuthor);
        bookAuthors.add(bookAuthor);

    }

    public void deleteAuthorBook(BookAuthor bookAuthor) {
        if (bookAuthors.contains(bookAuthor)) {
            bookAuthors.remove(bookAuthor);
            bookAuthor.removeBookAuthor();
        }
    }

    private void validateBookAuthor(BookAuthor bookAuthor) {
        if (bookAuthor == null) {
            throw new ValidationException("book author can't be null");
        }
        if (bookAuthor.getAuthor() != this) {
            throw new ValidationException("BookAuthor attach to another book");
        }
        if (bookAuthor.getBook() == null) {
            throw new ValidationException("Author should be attached to the author book");
        }

    }


    public List<BookAuthor> getBookAuthors() {
        return new ArrayList<>(bookAuthors);
    }

    public void setName(String fullName) {
        validateFullName(fullName);
        this.fullName = fullName;
    }

    public String getName() {
        return fullName;
    }

}
