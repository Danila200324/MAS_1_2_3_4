package com.danven.web_library.service.book_service.pagination;

import com.danven.web_library.domain.book.Book;
import com.danven.web_library.service.book_service.BookService;
import com.danven.web_library.service.book_service.sorting.BookServiceSortStrategy;

import java.util.ArrayList;
import java.util.List;

public class BookSimplePaginationStrategyService extends BookService {

    private final int pageSize;

    public BookSimplePaginationStrategyService(BookServiceSortStrategy sortStrategy, int pageSize) {
        super(sortStrategy);
        this.pageSize = pageSize;
    }

    @Override
    public List<Book> getBooks(int page) {
        List<Book> books = new ArrayList<>(Book.getAllRecords());
        sortStrategy.sort(books);

        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, books.size());
        return new ArrayList<>(books.subList(startIndex, endIndex));
    }


}
