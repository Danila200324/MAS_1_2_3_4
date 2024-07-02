package com.danven.web_library.domain.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PaperDiskBookTest {

    private PaperDiskBook paperBook;

    private Set<Category> categories;

    @BeforeEach
    void setUp() {
        categories = new HashSet<>();
        categories.add(new Category("Fiction", "Fiction books"));

        paperBook = new PaperDiskBook(
                "Moby Dick",
                1851,
                "A book about a whale",
                "Herman Melville",
                "English",
                categories,
                500,
                5.0,
                DiskFormat.CD);
    }

    @Test
    public void testSuccessfulCreatePaperDiskBook() {
        assertNotNull(paperBook);
        assertEquals("Moby Dick", paperBook.getName());
        assertEquals(1851, paperBook.getYearOfPublishing());
        assertEquals("A book about a whale", paperBook.getDescription());
        assertEquals("Herman Melville", paperBook.getAuthor());
        assertEquals("English", paperBook.getLanguage());
        assertEquals(500, paperBook.getNumberOfPages());
        assertTrue(paperBook.getCategories().containsAll(categories));
        assertEquals(5.0, paperBook.getDurationInHours());
        assertEquals(DiskFormat.CD, paperBook.getDiskFormat());
    }

    @Test
    public void testSuccessfulSettingData(){
        paperBook.setDiskFormat(DiskFormat.DVD);
        paperBook.setDurationInHours(6.0);

        assertEquals(6.0, paperBook.getDurationInHours());
        assertEquals(DiskFormat.DVD, paperBook.getDiskFormat());
    }


}
