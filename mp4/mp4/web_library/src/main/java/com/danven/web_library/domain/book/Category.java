package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    private static final List<Category> records = new ArrayList<>();

    private String name;

    private String description;

    private final Set<Book> attachedBooks = new HashSet<>();

    private final Set<Book> attachedBooksWhereCategoryIsPrimarily = new HashSet<>();

    public Category(String name, String description) {
        validateName(name);
        validateDescription(description);

        this.name = name;
        this.description = description;

        records.add(this);
    }

    public void addAttachedBook(Book book) {
        validateBook(book);
        if (!attachedBooks.contains(book)) {
            attachedBooks.add(book);
            book.addCategory(this);
        }
    }

    public void removeAttachedBook(Book book) {
        validateBook(book);
        if (attachedBooks.contains(book)) {
            attachedBooks.remove(book);
            book.removeCategory(this);
        }
    }

    public void addBookWhereCategoryIsPrimarily(Book book) {
        validateBook(book);
        if (!attachedBooksWhereCategoryIsPrimarily.contains(book)) {
            attachedBooksWhereCategoryIsPrimarily.add(book);
            book.addPrimarilyCategory(this);
        }
    }

    public void removeBookWhereCategoryIsPrimarily(Book book) {
        validateBook(book);
        if (attachedBooksWhereCategoryIsPrimarily.contains(book)) {
            attachedBooksWhereCategoryIsPrimarily.remove(book);
            book.removePrimarilyCategory(this);
        }
    }

    private static void validateBook(Book book) {
        if (book == null) {
            throw new ValidationException("Book can't be null");
        }
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Book> getAttachedBooks() {
        return new HashSet<>(attachedBooks);
    }

    public Set<Book> getAttachedBooksWhereCategoryIsPrimarily() {
        return new HashSet<>(attachedBooksWhereCategoryIsPrimarily);
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name should be present.");
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new ValidationException("Description should be present.");
        }
    }

    public static List<Category> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
