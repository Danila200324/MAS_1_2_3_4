package com.danven.web_library.user;

import com.danven.web_library.domain.book.Book;
import com.danven.web_library.domain.book.Category;
import com.danven.web_library.domain.book.PBook;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class BookCategoryTest {

    private Book book;
    private Category cat1, cat2, cat3;

    @BeforeEach
    public void setUp() {
        cat1 = new Category("Fiction", "Fictional works");
        cat2 = new Category("Science Fiction", "A subset of fiction");
        cat3 = new Category("Drama", "Drama book");
        Set<Category> categories = new HashSet<>();
        categories.add(cat1);
        categories.add(cat3);

        Set<Category> primarilyCategories = new HashSet<>();
        primarilyCategories.add(cat1);
        book = new PBook("Dune", 1965, "A book about a desert planet", categories,
                primarilyCategories, "English", 2007, 45);
    }

    @Test
    public void addPrimarilyCategoryThatExists() {
        book.addCategory(cat2);
        book.addPrimarilyCategory(cat2);
        assertTrue(book.getPrimarilyCategories().contains(cat2));
    }

    @Test
    public void testCategoriesBackReferences(){
        book.addCategory(cat2);
        book.addPrimarilyCategory(cat2);
        assertTrue(cat2.getAttachedBooksWhereCategoryIsPrimarily().contains(book));
        assertTrue(cat2.getAttachedBooks().contains(book));

        book.removeCategory(cat2);
        assertFalse(cat2.getAttachedBooksWhereCategoryIsPrimarily().contains(book));
        assertFalse(cat2.getAttachedBooks().contains(book));
    }

    @Test
    public void addPrimarilyCategoryThatDoesNotExist() {
        Category cat3 = new Category("Non-Fiction", "Non-fictional works");
        assertThrows(ValidationException.class, () -> book.addPrimarilyCategory(cat3));
    }

    @Test
    public void removeCategoryAlsoRemovesFromPrimarily() {
        book.addCategory(cat2);
        book.addPrimarilyCategory(cat2);
        book.removeCategory(cat2);
        assertFalse(book.getAllCategories().contains(cat2));
        assertFalse(book.getPrimarilyCategories().contains(cat2));
    }

}
