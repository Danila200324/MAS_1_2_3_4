package com.danven.web_library.domain.book;

import com.danven.web_library.Factory;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


public class PBookTest {

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

    private static final int VALID_NUMBER_OF_PAGES_1 = 20;

    private static final int VALID_NUMBER_OF_PAGES_2 = 30;

    private static final int INVALID_NUMBER_OF_PAGES_1 = -3;


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
    public void testCreatePBookWithValidParameters() {

        PBook book = new PBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setImages(VALID_IMAGES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setAuthor(VALID_AUTHOR_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .setNumberOfPages(VALID_NUMBER_OF_PAGES_1)
                .build();

        Assertions.assertEquals(VALID_NUMBER_OF_PAGES_1, book.getNumberOfPages());
    }


    @Test
    public void updatePBookWithValidParameters() {

        PBook book = new PBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setImages(VALID_IMAGES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setAuthor(VALID_AUTHOR_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .setNumberOfPages(VALID_NUMBER_OF_PAGES_1)
                .build();

        book.setNumberOfPages(VALID_NUMBER_OF_PAGES_2);

        Assertions.assertEquals(VALID_NUMBER_OF_PAGES_2, book.getNumberOfPages());
    }

    @Test
    public void createPBookWithInvalidNumberOfPagesThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new PBook.BookBuilder()
                        .setCategories(VALID_CATEGORIES_1)
                        .setImages(VALID_IMAGES_1)
                        .setLanguage(VALID_LANGUAGE_1)
                        .setAuthor(VALID_AUTHOR_1)
                        .setName(VALID_NAME_1)
                        .setDescription(VALID_DESCRIPTION_1)
                        .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                        .setNumberOfPages(INVALID_NUMBER_OF_PAGES_1)
                        .build()
        );
    }

    @Test
    public void throwValidationExceptionWhenSettingInvalidNumberOfPages() {
        Assertions.assertThrows(ValidationException.class,
                () -> new PBook.BookBuilder()
                        .setCategories(VALID_CATEGORIES_1)
                        .setImages(VALID_IMAGES_1)
                        .setLanguage(VALID_LANGUAGE_1)
                        .setAuthor(VALID_AUTHOR_1)
                        .setName(VALID_NAME_1)
                        .setDescription(VALID_DESCRIPTION_1)
                        .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                        .setNumberOfPages(INVALID_NUMBER_OF_PAGES_1)
                        .build()
        );
    }

}
