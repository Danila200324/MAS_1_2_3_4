package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageTest {

    private static final String FILE_PATH_1 = "src/test/testData/book1(jpeg).jpeg";
    private static final String FILE_PATH_2 = "src/test/testData/book2(jpeg).jpeg";
    private static final String FORMAT = "jpeg";
    private static final Path PATH_FIRST = Paths.get(FILE_PATH_1);
    private static final Path PATH_SECOND = Paths.get(FILE_PATH_2);
    private static byte[] VALID_BYTES_1;
    private static byte[] VALID_BYTES_2;

    private static final byte[] INVALID_BYTES = null;
    private static final String INVALID_FORMAT = "";

    static {
        try {
            VALID_BYTES_1 = Files.readAllBytes(PATH_FIRST);
            VALID_BYTES_2 = Files.readAllBytes(PATH_SECOND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCreateImageWithValidParameters() {
        Image validImage = new Image.ImageBuilder()
                .setImage(VALID_BYTES_1)
                .setFormat(FORMAT)
                .build();

        Assertions.assertEquals(VALID_BYTES_1, validImage.getImage());
        Assertions.assertEquals(FORMAT, validImage.getFormat());
    }

    @Test
    public void testUpdateImageWithValidParameters() {
        Image validImage = new Image.ImageBuilder()
                .setImage(VALID_BYTES_1)
                .setFormat(FORMAT)
                .build();

        validImage.setImage(VALID_BYTES_2);
        validImage.setFormat(FORMAT);

        Assertions.assertEquals(VALID_BYTES_2, validImage.getImage());
        Assertions.assertEquals(FORMAT, validImage.getFormat());
    }


    @Test
    public void createImageWithInvalidBytesThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Image.ImageBuilder()
                        .setImage(INVALID_BYTES)
                        .setFormat(FORMAT)
                        .build()
        );
    }

    @Test
    public void createImageWithInvalidFormatThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Image.ImageBuilder()
                        .setImage(VALID_BYTES_1)
                        .setFormat(INVALID_FORMAT)
                        .build()
        );
    }

    @Test
    public void createImageWithNotAllAttributes() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Image.ImageBuilder()
                        .setImage(VALID_BYTES_1)
                        .build()
        );
    }


    @Test
    public void testThrowValidationExceptionWhenSettingInvalidBytes() {
        Image validImage = new Image.ImageBuilder()
                .setImage(VALID_BYTES_1)
                .setFormat(FORMAT)
                .build();

        Assertions.assertThrows(ValidationException.class, () -> validImage.setImage(INVALID_BYTES));
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidFormat() {
        Image validImage = new Image.ImageBuilder()
                .setImage(VALID_BYTES_1)
                .setFormat(FORMAT)
                .build();

        Assertions.assertThrows(ValidationException.class, () -> validImage.setFormat(INVALID_FORMAT));
    }


}
