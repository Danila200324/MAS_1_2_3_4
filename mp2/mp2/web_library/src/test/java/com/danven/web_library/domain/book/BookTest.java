package com.danven.web_library.domain.book;

import com.danven.web_library.Factory;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BookTest {

    private static Set<Category> VALID_CATEGORIES_1;

    private static Set<Category> VALID_CATEGORIES_2;

    private static final Language VALID_LANGUAGE_1 = Factory.getLanguage("ENGLISH", "EN");

    private static final Language VALID_LANGUAGE_2 = Factory.getLanguage("POLISH", "POL");

    private static final String VALID_NAME_1 = "Book 1";

    private static final String VALID_NAME_2 = "Book 2";

    private static final String VALID_DESCRIPTION_1 = "Description 1";

    private static final String VALID_DESCRIPTION_2 = "Description_2";

    private static final int VALID_YEAR_OF_PUBLISHING_1 = 1978;

    private static final int VALID_YEAR_OF_PUBLISHING_2 = 2002;

    private static final LocalDate VALID_YEAR_OF_PUBLISHING_LOCAL_DATE = LocalDate.of(2020, 9, 9);

    private static final String INVALID_NAME = "\s";

    private static final String INVALID_DESCRIPTION = "\s";

    private static final String INVALID_AUTHOR = "\s";

    private static final int INVALID_YEAR_OF_PUBLISHING = LocalDate.now().getYear() + 1;

    private static final Language INVALID_LANGUAGE = null;

    private static final Set<Category> INVALID_CATEGORIES = new HashSet<>();


    private static Image VALID_IMAGE_1 =
            Factory.getImage("src/test/testData/book1(jpeg).jpeg", "jpeg");

    private static Image VALID_IMAGE_2 =
            Factory.getImage("src/test/testData/book2(jpeg).jpeg", "jpeg");



    @BeforeEach
    public void initialize() {

        VALID_CATEGORIES_1 = new HashSet<>();
        VALID_CATEGORIES_1.add(Factory.getCategory("Drama", "Drama books..."));
        VALID_CATEGORIES_1.add(Factory.getCategory("History", "History books"));

        VALID_CATEGORIES_2 = new HashSet<>();
        VALID_CATEGORIES_2.add(Factory.getCategory("Science", "Science books..."));
        VALID_CATEGORIES_2.add(Factory.getCategory("Music", "Music books"));

    }

    private static class TestableBook extends Book {

        private TestableBook(BookBuilder builder) {
            super(builder);
        }

        public static class BookBuilder extends Book.BookBuilder<TestableBook.BookBuilder> {

            public BookBuilder() {
            }

            @Override
            public TestableBook build() {
                return new TestableBook(this);
            }

            @Override
            protected TestableBook.BookBuilder self() {
                return this;
            }

        }

    }

    @Test
    public void createBookWithValidParameters() {
        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        Assertions.assertIterableEquals(VALID_CATEGORIES_1, book.getCategories());
        Assertions.assertEquals(VALID_LANGUAGE_1, book.getLanguage());
        Assertions.assertEquals(VALID_NAME_1, book.getName());
        Assertions.assertEquals(VALID_DESCRIPTION_1, book.getDescription());
        Assertions.assertEquals(VALID_YEAR_OF_PUBLISHING_1, book.getYearOfPublishing());
    }

    @Test
    public void updateBookWithValidParameters() {
        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        book.setName(VALID_NAME_2);
        book.setDescription(VALID_DESCRIPTION_2);
        book.setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_2);
        book.setCategories(VALID_CATEGORIES_2);
        book.setLanguage(VALID_LANGUAGE_2);

        Assertions.assertIterableEquals(VALID_CATEGORIES_2, book.getCategories());
        Assertions.assertEquals(VALID_LANGUAGE_2, book.getLanguage());
        Assertions.assertEquals(VALID_NAME_2, book.getName());
        Assertions.assertEquals(VALID_DESCRIPTION_2, book.getDescription());
        Assertions.assertEquals(VALID_YEAR_OF_PUBLISHING_2, book.getYearOfPublishing());
    }

    @Test
    public void updateBookWithValidAgeOfBookWithOverloadedMethod() {
        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        book.setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_LOCAL_DATE);

        Assertions.assertEquals(VALID_YEAR_OF_PUBLISHING_LOCAL_DATE.getYear(), book.getYearOfPublishing());
    }


    @Test
    public void createBookWithInvalidNameThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new TestableBook.BookBuilder()
                        .setCategories(VALID_CATEGORIES_1)
                        .setLanguage(VALID_LANGUAGE_1)
                        .setName(INVALID_NAME)
                        .setDescription(VALID_DESCRIPTION_1)
                        .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                        .build()
        );
    }

    @Test
    public void createBookWithInvalidDescriptionSuccessful() {
        Assertions.assertDoesNotThrow(
                () -> new TestableBook.BookBuilder()
                        .setCategories(VALID_CATEGORIES_1)
                        .setLanguage(VALID_LANGUAGE_1)
                        .setName(VALID_NAME_1)
                        .setDescription(INVALID_DESCRIPTION)
                        .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                        .build()
        );
    }

    @Test
    public void createBookWithInvalidAuthorThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new TestableBook.BookBuilder()
                        .setCategories(VALID_CATEGORIES_1)
                        .setLanguage(VALID_LANGUAGE_1)
                        .setName(INVALID_NAME)
                        .setDescription(VALID_DESCRIPTION_1)
                        .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                        .build()
        );
    }

    @Test
    public void createBookWithInvalidYearOfPublishingThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new TestableBook.BookBuilder()
                        .setCategories(VALID_CATEGORIES_1)
                        .setLanguage(VALID_LANGUAGE_1)
                        .setName(VALID_NAME_1)
                        .setDescription(VALID_DESCRIPTION_1)
                        .setYearOfPublishing(INVALID_YEAR_OF_PUBLISHING)
                        .build()
        );
    }

    @Test
    public void createBookWithInvalidLanguageThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new TestableBook.BookBuilder()
                        .setCategories(VALID_CATEGORIES_1)
                        .setLanguage(INVALID_LANGUAGE)
                        .setName(VALID_NAME_1)
                        .setDescription(VALID_DESCRIPTION_1)
                        .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                        .build()
        );
    }

    @Test
    public void createBookWithInvalidCategoriesThrowsException() {

        Assertions.assertThrows(ValidationException.class,
                () -> new TestableBook.BookBuilder()
                        .setCategories(INVALID_CATEGORIES)
                        .setLanguage(INVALID_LANGUAGE)
                        .setName(VALID_NAME_1)
                        .setDescription(VALID_DESCRIPTION_1)
                        .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                        .build()
        );
    }

    @Test
    public void throwValidationExceptionWhenSettingInvalidName(){
        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> book.setName(INVALID_NAME)
        );
    }

    @Test
    public void throwValidationExceptionWhenSettingInvalidYearOfPublishing(){
        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> book.setYearOfPublishing(INVALID_YEAR_OF_PUBLISHING)
        );
    }


    @Test
    public void throwValidationExceptionWhenSettingInvalidCategories(){
        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> book.setCategories(INVALID_CATEGORIES)
        );
    }


    @Test
    public void throwValidationExceptionWhenSettingInvalidLanguage(){
        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> book.setLanguage(INVALID_LANGUAGE)
        );
    }

    @Test
    public void addCategoryToCollection() {
        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        Category additionalCategory = Factory.getCategory("Romantic", "Romantic books...");

        book.addCategory(additionalCategory);

        VALID_CATEGORIES_1.add(additionalCategory);

        Assertions.assertIterableEquals(VALID_CATEGORIES_1, book.getCategories());
    }


    @Test
    public void deletingCategoryWhenListContainsOneValueShouldThrowException() {

        Set<Category> categoriesWithOneValue = new HashSet<>();
        categoriesWithOneValue.add(
                Factory.getCategory("Category 1", "Description for category 1")
        );

        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(categoriesWithOneValue)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();

        Iterator<Category> categoryIterator = book.getCategories().iterator();

        Category category = categoryIterator.next();

        Assertions.assertThrows(ValidationException.class,
                () -> book.deleteCategory(category)
        );

        Assertions.assertThrows(ValidationException.class,
                () -> book.deleteCategory(null)
        );
    }

    @Test
    public void testGetAgeOfBook(){
        TestableBook book = new TestableBook.BookBuilder()
                .setCategories(VALID_CATEGORIES_1)
                .setLanguage(VALID_LANGUAGE_1)
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .setYearOfPublishing(VALID_YEAR_OF_PUBLISHING_1)
                .build();
        int age = LocalDate.now().getYear() - VALID_YEAR_OF_PUBLISHING_1;
        Assertions.assertEquals(age, book.getAgeOfBook());
    }



}
