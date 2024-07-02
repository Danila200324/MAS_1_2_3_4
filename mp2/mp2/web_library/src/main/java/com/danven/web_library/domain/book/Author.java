package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Author implements Serializable {

    @Serial
    private static final long serialVersionUID = 56467L;

    private String fullName;

    private final Set<BookAuthor> bookAuthors = new HashSet<>();

    private Author(AuthorBuilder authorBuilder) {
        validateFullName(authorBuilder.fullName);

        this.fullName = authorBuilder.fullName;
    }

    public static class AuthorBuilder {
        private String fullName;

        public Author.AuthorBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Author build() {
            return new Author(this);
        }
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
            bookAuthor.deleteBookAuthor();
        }
    }

    void cleanAuthorBookFromSet(BookAuthor bookAuthor) {
        bookAuthors.remove(bookAuthor);
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


    public Set<BookAuthor> getBookAuthors() {
        return new HashSet<>(bookAuthors);
    }

    public void setName(String fullName) {
        validateFullName(fullName);
        this.fullName = fullName;
    }

    public String getName() {
        return fullName;
    }

}
