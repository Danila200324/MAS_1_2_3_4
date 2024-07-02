package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CategoryTest {

    private static final String VALID_NAME_1 = "Documentary";
    private static final String VALID_DESCRIPTION_1 = "Documentary books offer factual, in-depth looks...";
    private static final String VALID_NAME_2 = "Science";
    private static final String VALID_DESCRIPTION_2 = "Science books delve into the wonders...";
    private static final String INVALID_NAME = "";
    private static final String DEFAULT_DESCRIPTION = "No description available.";


    @Test
    public void createCategoryWithValidParameters() {
        Category category = new Category.CategoryBuilder()
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .build();

        Assertions.assertEquals(VALID_NAME_1, category.getName());
        Assertions.assertEquals(VALID_DESCRIPTION_1, category.getDescription());
    }

    @Test
    public void updateCategoryWithValidParameters() {
        Category category = new Category.CategoryBuilder()
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .build();

        category.setName(VALID_NAME_2);
        category.setDescription(VALID_DESCRIPTION_2);

        Assertions.assertEquals(VALID_NAME_2, category.getName());
        Assertions.assertEquals(VALID_DESCRIPTION_2, category.getDescription());
    }

    @Test
    public void createCategoryWithInvalidNameThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Category.CategoryBuilder()
                        .setName(INVALID_NAME)
                        .setDescription(VALID_DESCRIPTION_1)
                        .build()
        );
    }

    @Test
    public void createCategoryWithDefaultDescription(){
        Category category = new Category.CategoryBuilder()
                .setName(VALID_NAME_1)
                .build();

        Assertions.assertEquals(DEFAULT_DESCRIPTION, category.getDefaultDescription());
    }

    @Test
    public void throwValidationExceptionWhenSettingInvalidName() {
        Category category = new Category.CategoryBuilder()
                .setName(VALID_NAME_1)
                .setDescription(VALID_DESCRIPTION_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> category.setName(INVALID_NAME));
    }

}
