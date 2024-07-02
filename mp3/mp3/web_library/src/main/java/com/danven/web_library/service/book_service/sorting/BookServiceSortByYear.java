package com.danven.web_library.service.book_service.sorting;

import com.danven.web_library.domain.book.Book;

import java.util.Comparator;
import java.util.List;

public class BookServiceSortByYear implements BookServiceSortStrategy {

    @Override
    public void sort(List<Book> books) {
        books.sort(Comparator.comparingInt(Book::getYearOfPublishing));
    }

}
