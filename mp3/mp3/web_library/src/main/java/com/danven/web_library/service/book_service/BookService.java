package com.danven.web_library.service.book_service;

import com.danven.web_library.domain.book.Book;
import com.danven.web_library.exceptions.ValidationException;
import com.danven.web_library.service.book_service.sorting.BookServiceSortStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class BookService {

    private static final List<BookService> records = new ArrayList<>();

    protected final BookServiceSortStrategy sortStrategy;

    public BookService(BookServiceSortStrategy sortStrategy) {

        validateSortStrategy(sortStrategy);

        this.sortStrategy = sortStrategy;

        records.add(this);
    }

    protected abstract List<Book> getBooks(int page);

    private void validateSortStrategy(BookServiceSortStrategy bookServiceSortStrategy) {
        if (bookServiceSortStrategy == null) {
            throw new ValidationException("Book strategy can't be null");
        }
    }

    public static List<BookService> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookService that = (BookService) o;
        return Objects.equals(sortStrategy, that.sortStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortStrategy);
    }
}
