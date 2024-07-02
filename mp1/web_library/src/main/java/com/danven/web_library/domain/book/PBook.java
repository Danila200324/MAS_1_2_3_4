package com.danven.web_library.domain.book;


import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;


public class PBook extends Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 6L;

    private static final List<PBook> records = new ArrayList<>();

    private int numberOfPages;

    protected PBook() {
        super();
    }

    private PBook(BookBuilder builder) {
        super(builder);

        validateNumberOfPages(builder.numberOfPages);

        this.numberOfPages = builder.numberOfPages;

        records.add(this);
    }


    public static class BookBuilder extends Book.BookBuilder<PBook.BookBuilder> {

        private int numberOfPages;

        @Override
        protected BookBuilder self() {
            return this;
        }

        public BookBuilder setNumberOfPages(int numberOfPages) {
            this.numberOfPages = numberOfPages;
            return this;
        }

        public PBook build() {
            return new PBook(this);
        }
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        validateNumberOfPages(numberOfPages);
        this.numberOfPages = numberOfPages;
    }

    public static void validateNumberOfPages(int numberOfPages) {
        if (numberOfPages <= 0) {
            throw new ValidationException("Number of pages should be greater than zero");
        }
    }

    public static List<Book> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PBook pBook = (PBook) o;
        return numberOfPages == pBook.numberOfPages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPages);
    }

    @Override
    public String toString() {
        return "PBook{" +
                "name='" + name + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", categories=" + categories +
                ", images=" + images +
                ", language=" + language +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
