package com.danven.web_library.domain.book;


import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class PaperBook extends Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 6L;

    private static final List<PaperBook> records = new ArrayList<>();

    private int numberOfPages;

    public PaperBook(String name, int yearOfPublishing, String description, String author, String language,
                     Set<Category> categories, int numberOfPages) {

        super(name, yearOfPublishing, description, author, language, categories);

        validateNumber0fPages(numberOfPages);

        this.numberOfPages = numberOfPages;

        records.add(this);
    }

    private void validateNumber0fPages(int numberOfPages) {
        if (numberOfPages <= 0) {
            throw new ValidationException("Number of pages should be less or equal to zero");
        }
    }

    public void setNumberOfPages(int numberOfPages) {
        validateNumber0fPages(numberOfPages);

        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public static List<Book> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperBook paperBook = (PaperBook) o;
        return numberOfPages == paperBook.numberOfPages;
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
                ", categories=" + categories +
                ", language=" + language +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
