package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LanguageTest {

    private static final String VALID_NAME_ENGLISH = "English";
    private static final String VALID_ACRONYM_ENGLISH = "EN";
    private static final String VALID_NAME_POLISH = "Polish";
    private static final String VALID_ACRONYM_POLISH = "PL";
    private static final String INVALID_NAME = "";
    private static final String INVALID_ACRONYM = null;

    @Test
    public void testCreateLanguageWithValidParameters() {
        Language language = new Language.LanguageBuilder()
                .setName(VALID_NAME_ENGLISH)
                .setAcronym(VALID_ACRONYM_ENGLISH)
                .build();

        Assertions.assertEquals(VALID_NAME_ENGLISH, language.getName());
        Assertions.assertEquals(VALID_ACRONYM_ENGLISH, language.getAcronym());
    }

    @Test
    public void testUpdateLanguageWithValidParameters() {
        Language language = new Language.LanguageBuilder()
                .setName(VALID_NAME_ENGLISH)
                .setAcronym(VALID_ACRONYM_ENGLISH)
                .build();

        language.setName(VALID_NAME_POLISH);
        language.setAcronym(VALID_ACRONYM_POLISH);

        Assertions.assertEquals(VALID_NAME_POLISH, language.getName());
        Assertions.assertEquals(VALID_ACRONYM_POLISH, language.getAcronym());
    }

    @Test
    public void createLanguageWithInvalidNameThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Language.LanguageBuilder()
                        .setName(INVALID_NAME)
                        .setAcronym(VALID_ACRONYM_ENGLISH)
                        .build()
        );
    }

    @Test
    public void createLanguageWithInvalidAcronymThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Language.LanguageBuilder()
                        .setAcronym(INVALID_ACRONYM)
                        .setName(INVALID_NAME)
                        .build()
        );
    }

    @Test
    public void createLanguageWithNotAllAttributes(){
        Assertions.assertThrows(ValidationException.class, () ->
                new Language.LanguageBuilder()
                        .setAcronym(VALID_ACRONYM_ENGLISH).build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidName() {
        Language language = new Language.LanguageBuilder()
                .setName(VALID_NAME_ENGLISH)
                .setAcronym(VALID_ACRONYM_ENGLISH)
                .build();

        Assertions.assertThrows(ValidationException.class, () -> language.setName(INVALID_NAME));
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidAcronym() {
        Language language = new Language.LanguageBuilder()
                .setName(VALID_NAME_ENGLISH)
                .setAcronym(VALID_ACRONYM_ENGLISH)
                .build();

        Assertions.assertThrows(ValidationException.class, () -> language.setAcronym(INVALID_ACRONYM));
    }


}
