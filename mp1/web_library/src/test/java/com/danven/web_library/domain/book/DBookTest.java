package com.danven.web_library.domain.book;

import com.danven.web_library.Factory;
import com.danven.web_library.exceptions.ValidationException;
import com.danven.web_library.persistence.PersistenceManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


public class DBookTest {

    private static Set<Image> VALID_IMAGES_1;

    private static Set<Image> VALID_IMAGES_2;

    private static Set<Category> VALID_CATEGORIES_1;

    private static Set<Category> VALID_CATEGORIES_2;

    private static final Language VALID_LANGUAGE_1 = Factory.getLanguage("ENGLISH", "EN");

    private static final Language VALID_LANGUAGE_2 = Factory.getLanguage("POLISH", "POL");

    private static final String VALID_NAME_1 = "Book 1";

    private static final String VALID_NAME_2 = "Book 2";

    private static final String VALID_AUTHOR_1 = "Author 1";

    private static final String VALID_AUTHOR_2 = "Author 2";

    private static final String VALID_DESCRIPTION_1 = "Description 1";

    private static final String VALID_DESCRIPTION_2 = "Description_2";

    private static final int VALID_YEAR_OF_PUBLISHING_1 = 1978;

    private static final int VALID_YEAR_OF_PUBLISHING_2 = 2002;

    private static final DiskFormat INVALID_DISK_FORMAT = null;

    private static final int INVALID_DURATION_IN_HOURS = -1;

    private static final DiskFormat VALID_DISK_FORMAT_1 = DiskFormat.CD;

    private static final DiskFormat VALID_DISK_FORMAT_2 = DiskFormat.DVD;

    private static final int VALID_DURATION_IN_HOURS_1 = 5;

    private static final int VALID_DURATION_IN_HOURS_2 = 7;


    @BeforeEach
    public void initialize() {

        VALID_IMAGES_1 = new HashSet<>();
        VALID_IMAGES_1.add(Factory.getImage("src/test/testData/book1(jpeg).jpeg", "jpeg"));
        VALID_IMAGES_1.add(Factory.getImage("src/test/testData/book2(jpeg).jpeg", "jpeg"));


        VALID_IMAGES_2 = new HashSet<>();
        VALID_IMAGES_2.add(Factory.getImage("src/test/testData/book3(jpeg).jpeg", "jpeg"));
        VALID_IMAGES_2.add(Factory.getImage("src/test/testData/book4(jpeg).jpeg", "jpeg"));


        VALID_CATEGORIES_1 = new HashSet<>();
        VALID_CATEGORIES_1.add(Factory.getCategory("Drama", "Drama books..."));
        VALID_CATEGORIES_1.add(Factory.getCategory("History", "History books"));

        VALID_CATEGORIES_2 = new HashSet<>();
        VALID_CATEGORIES_2.add(Factory.getCategory("Science", "Science books..."));
        VALID_CATEGORIES_2.add(Factory.getCategory("Music", "Music books"));
    }

    @Test
    public void testCreateDBookWithValidParameters() {

        DBook book = new DBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setImages(VALID_IMAGES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setAuthor(VALID_AUTHOR_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setDiskFormat(VALID_DISK_FORMAT_1)
                .setDurationInHours(VALID_DURATION_IN_HOURS_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        Assertions.assertEquals(VALID_DURATION_IN_HOURS_1, book.getDurationInHours());
        Assertions.assertEquals(VALID_YEAR_OF_PUBLISHING_1, book.getYearOfPublishing());
    }

    @Test
    public void updateDBookWithValidParameters() {

        DBook book = new DBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setImages(VALID_IMAGES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setAuthor(VALID_AUTHOR_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setDiskFormat(VALID_DISK_FORMAT_1)
                .setDurationInHours(VALID_DURATION_IN_HOURS_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        book.setDurationInHours(VALID_DURATION_IN_HOURS_2);
        book.setDiskFormat(VALID_DISK_FORMAT_2);

        Assertions.assertEquals(VALID_DURATION_IN_HOURS_2, book.getDurationInHours());
        Assertions.assertEquals(VALID_DISK_FORMAT_2, book.getDiskFormat());
    }

    @Test
    public void createDBookWithInvalidDurationInHoursThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new DBook.BookBuilder()
                        .setCategories(VALID_CATEGORIES_1)
                        .setImages(VALID_IMAGES_1)
                        .setLanguage(VALID_LANGUAGE_1)
                        .setAuthor(VALID_AUTHOR_1)
                        .setName(VALID_NAME_1)
                        .setDescription(VALID_DESCRIPTION_1)
                        .setDiskFormat(VALID_DISK_FORMAT_1)
                        .setDurationInHours(INVALID_DURATION_IN_HOURS)
                        .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                        .build()
        );
    }

    @Test
    public void createDBookWithInvalidDiskFormatThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new DBook.BookBuilder()
                        .setCategories(VALID_CATEGORIES_1)
                        .setImages(VALID_IMAGES_1)
                        .setLanguage(VALID_LANGUAGE_1)
                        .setAuthor(VALID_AUTHOR_1)
                        .setName(VALID_NAME_1)
                        .setDescription(VALID_DESCRIPTION_1)
                        .setDiskFormat(INVALID_DISK_FORMAT)
                        .setDurationInHours(VALID_DURATION_IN_HOURS_1)
                        .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                        .build()
        );
    }

    @Test
    public void throwValidationExceptionWhenSettingInvalidDurationInHours(){
        DBook book = new DBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setImages(VALID_IMAGES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setAuthor(VALID_AUTHOR_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setDiskFormat(VALID_DISK_FORMAT_1)
                .setDurationInHours(VALID_DURATION_IN_HOURS_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> book.setDurationInHours(INVALID_DURATION_IN_HOURS)
        );
    }

    @Test
    public void throwValidationExceptionWhenSettingInvalidDiskFormat(){

        DBook book = new DBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setImages(VALID_IMAGES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setAuthor(VALID_AUTHOR_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setDiskFormat(VALID_DISK_FORMAT_1)
                .setDurationInHours(VALID_DURATION_IN_HOURS_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        PersistenceManager.saveAll();

        Assertions.assertThrows(ValidationException.class,
                () -> book.setDiskFormat(INVALID_DISK_FORMAT)
        );
    }

    @Test
    public void t(){
        PersistenceManager.loadAll();
    }


}
