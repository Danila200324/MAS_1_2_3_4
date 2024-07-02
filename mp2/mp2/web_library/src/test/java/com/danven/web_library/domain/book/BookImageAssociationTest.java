package com.danven.web_library.domain.book;

import com.danven.web_library.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookImageAssociationTest {

    private Book book_first;

    private Book book_second;

    private Book book_null;

    private Image image_first;

    @BeforeEach
    public void setup() {
        book_first = Factory.getDBookWithAuthorAndDefaultParameters("Book 1");
        book_second = Factory.getPBookWithAuthorAndDefaultParameters("Book 2");
        book_null = null;

        image_first = Factory.getImage("src/test/testData/book1(jpeg).jpeg", "jpeg");
    }

    @Test
    void testAddImageToBook() {
        //before
        assertEquals(0, book_first.getImages().size(), "Book should contain 0 images");

        //operation
        book_first.addImage(image_first);

        //after
        assertEquals(1, book_first.getImages().size(), "Book should contain 1 image");
        assertTrue(book_first.getImages().contains(image_first), "Book should contain the image_first");
        assertEquals(book_first, image_first.getBook(), "Image's book reference should point to the correct book");
    }

    @Test
    public void testAddBookToImage(){
        //before
        assertNull(image_first.getBook(), "Image book should be null");
        assertEquals(0, book_first.getImages().size(), "Book should contain 0 images");

        //operation
        image_first.setBook(book_first);

        //after
        assertEquals(1, book_first.getImages().size(), "Book should contain 1 image");
        assertTrue(book_first.getImages().contains(image_first), "Book should contain the image_first");
        assertEquals(book_first, image_first.getBook(), "Image's book reference should point to the correct book");
    }

    @Test
    public void testRemoveAssociationSettingNullToBookInImage(){
        //before
        assertNull(image_first.getBook(), "Image book should be null");
        assertEquals(0, book_first.getImages().size(), "Book should contain 0 images");

        //add image to the book
        book_first.addImage(image_first);

        //after adding
        assertEquals(1, book_first.getImages().size(), "Book should contain 1 image");
        assertTrue(book_first.getImages().contains(image_first), "Book should contain the image_first");
        assertEquals(book_first, image_first.getBook(), "Image's book reference should point to the correct book");

        //set null to the image
        image_first.setBook(book_null);

        //after setting null
        assertNull(image_first.getBook(), "Image book should be null");
        assertEquals(0, book_first.getImages().size(), "Book should contain 0 images");

    }

    @Test
    public void testRemoveAssociationDeletingImageToBook(){
        //before
        assertNull(image_first.getBook(), "Image book should be null");
        assertEquals(0, book_first.getImages().size(), "Book should contain 0 images");

        //add image to the book
        book_first.addImage(image_first);

        //after adding
        assertEquals(1, book_first.getImages().size(), "Book should contain 1 image");
        assertTrue(book_first.getImages().contains(image_first), "Book should contain the image_first");
        assertEquals(book_first, image_first.getBook(), "Image's book reference should point to the correct book");

        //delete image from the book
        book_first.deleteImage(image_first);

        //after deleting image
        assertNull(image_first.getBook(), "Image book should be null");
        assertEquals(0, book_first.getImages().size(), "Book should contain 0 images");

    }

    @Test
    public void testSettingAnotherBookToImage(){
        //before
        assertNull(image_first.getBook(), "Image book should be null");
        assertEquals(0, book_first.getImages().size(), "Book should contain 0 images");

        //add image to the book
        book_first.addImage(image_first);

        //after adding
        assertEquals(1, book_first.getImages().size(), "Book should contain 1 image");
        assertTrue(book_first.getImages().contains(image_first), "Book should contain the image_first");
        assertEquals(book_first, image_first.getBook(), "Image's book reference should point to the correct book");

        //set another book to image
        image_first.setBook(book_second);

        //after setting another book
        assertEquals(0, book_first.getImages().size(), "Book should contain 0 images");
        assertEquals(1, book_second.getImages().size(), "Second book should contain 1 image");
        assertEquals(book_second, image_first.getBook(), "Second image should contain second book");

    }


}
