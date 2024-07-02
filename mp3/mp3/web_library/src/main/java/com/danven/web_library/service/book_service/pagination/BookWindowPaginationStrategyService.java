package com.danven.web_library.service.book_service.pagination;

import com.danven.web_library.domain.book.Book;
import com.danven.web_library.service.book_service.BookService;
import com.danven.web_library.service.book_service.sorting.BookServiceSortStrategy;

import java.util.ArrayList;
import java.util.List;

public class BookWindowPaginationStrategyService extends BookService {

    private final int pageSize;

    public BookWindowPaginationStrategyService(BookServiceSortStrategy sortStrategy, int pageSize) {
        super(sortStrategy);

        this.pageSize = pageSize;
    }

    @Override
    public List<Book> getBooks(int page) {
        List<Book> books = new ArrayList<>(Book.getAllRecords());
        sortStrategy.sort(books);

        int totalBooks = books.size();
        int totalPages = (totalBooks + pageSize - 1) / pageSize;
        page = Math.max(0, Math.min(page, totalPages - 1));

        int centralIndex = page * pageSize + pageSize / 2;
        centralIndex = Math.max(0, Math.min(centralIndex, totalBooks - 1));

        int startIndex = Math.max(0, centralIndex - pageSize / 2);
        int endIndex = Math.min(totalBooks, centralIndex + pageSize / 2 + 1);

        return new ArrayList<>(books.subList(startIndex, endIndex));
    }

}
