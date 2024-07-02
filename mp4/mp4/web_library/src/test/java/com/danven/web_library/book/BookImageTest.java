package com.danven.web_library.book;



import com.danven.web_library.domain.book.Book;
import com.danven.web_library.domain.book.Category;
import com.danven.web_library.domain.book.Image;
import com.danven.web_library.domain.book.PBook;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


public class BookImageTest {

    private Book book;
    private Image image1, image2, previewImage;

    @BeforeEach
    public void setup() {

        Category cat1, cat3;
        cat1 = new Category("Fiction", "Fictional works");
        cat3 = new Category("Drama", "Drama book");

        Set<Category> categories = new HashSet<>();
        categories.add(cat1);
        categories.add(cat3);

        Set<Category> primarilyCategories = new HashSet<>();
        primarilyCategories.add(cat1);
        book = new PBook("Example Book", 2021,
                "An example description", categories, primarilyCategories,
                "English", 1203, 56);

        image1 = new Image(new byte[]{}, "src/test/testData/book1(jpeg).jpeg");
        image2 = new Image(new byte[]{}, "src/test/testData/book2(jpeg).jpeg");
        previewImage = new Image(new byte[]{}, "src/test/testData/book3(jpeg).jpeg");
    }

    @Test
    public void testAddImageAndPreviewImage() {
        book.addImage(image1);
        book.setPreviewImage(previewImage);

        Assertions.assertEquals(1, book.getImages().size());
        Assertions.assertEquals(book.getPreviewImage(), previewImage);
    }

    @Test
    public void testAddImageAndPreviewImageWithoutXOR() {
        book.addImage(image1);
        Assertions.assertThrows(ValidationException.class, () -> book.setPreviewImage(image1));
    }

    @Test
    public void testPreviewImageAndChangePreviewStatus() {
        book.setPreviewImage(image1);
        Assertions.assertTrue(image1.isPreview());
        Assertions.assertEquals(book.getPreviewImage(), image1);

        image1.setPreview(false);

        Assertions.assertFalse(image1.isPreview());
        Assertions.assertNull(book.getPreviewImage());
    }

    @Test
    public void testPreviewImageAndSetPreviewImageAsNull() {
        book.setPreviewImage(image1);
        Assertions.assertTrue(image1.isPreview());
        Assertions.assertEquals(book.getPreviewImage(), image1);

        book.setPreviewImage(null);

        Assertions.assertFalse(image1.isPreview());
        Assertions.assertNull(book.getPreviewImage());
    }

}
