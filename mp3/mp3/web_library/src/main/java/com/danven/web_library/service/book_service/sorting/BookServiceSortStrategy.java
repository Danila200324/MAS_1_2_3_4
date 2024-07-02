package com.danven.web_library.service.book_service.sorting;

import com.danven.web_library.domain.book.Book;

import java.util.List;

public interface BookServiceSortStrategy {

    void sort(List<Book> books);
}
