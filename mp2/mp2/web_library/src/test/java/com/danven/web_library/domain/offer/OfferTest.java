package com.danven.web_library.domain.offer;

import com.danven.web_library.Factory;
import com.danven.web_library.domain.book.Book;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class OfferTest {

    private static final LocalDateTime VALID_PUBLISHING_DATE_1 = LocalDateTime.of(2024, 1, 22, 9, 5);

    private static final LocalDateTime INVALID_PUBLISHING_DATE_FUTURE = LocalDateTime.now().plusDays(1);

    private static final double VALID_PRICE_1 = 45.0;

    private static final double VALID_PRICE_2 = 90.0;

    private static final double INVALID_PRICE_NEGATIVE = -1.0;

    private static final int VALID_NUMBER_OF_COPIES_1 = 3;

    private static final int VALID_NUMBER_OF_COPIES_2 = 2;

    private static final int INVALID_NUMBER_OF_COPIES = 0;

    private static final Book VALID_BOOK_1 =
            Factory.getPBookWithAuthorAndDefaultParameters("Book 1");

    private static final Book VALID_BOOK_2 =
            Factory.getPBookWithAuthorAndDefaultParameters("Book 2");

    private static final Book INVALID_BOOK_1 = null;

    private static final ContactInfo VALID_CONTACT_INFO_1 =
            Factory.getContactInfoWithEmailAndDefaultParameters("danven2018@gmail.com");

    private static final ContactInfo VALID_CONTACT_INFO_2 =
            Factory.getContactInfoWithEmailAndDefaultParameters("danven@gmail.com");

    private static final ContactInfo INVALID_CONTACT_INFO = null;


    @Test
    public void testCreateOfferWithValidParameters() {

        Offer offer = new Offer.Builder()
                .setPublishingTime(VALID_PUBLISHING_DATE_1)
                .setContactInfo(VALID_CONTACT_INFO_1)
                .setPrice(VALID_PRICE_1)
                .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                .setBook(VALID_BOOK_1)
                .build();

        Assertions.assertEquals(VALID_BOOK_1, offer.getBook());
        Assertions.assertEquals(VALID_PUBLISHING_DATE_1, offer.getPublishingTime());
        Assertions.assertEquals(VALID_CONTACT_INFO_1, offer.getContactInfo());
        Assertions.assertEquals(VALID_PRICE_1, offer.getPrice());
        Assertions.assertEquals(VALID_NUMBER_OF_COPIES_1, offer.getNumberOfCopies());

    }

    @Test
    public void updateOfferWithValidParameters() {
        Offer offer = new Offer.Builder()
                .setPublishingTime(VALID_PUBLISHING_DATE_1)
                .setContactInfo(VALID_CONTACT_INFO_1)
                .setPrice(VALID_PRICE_1)
                .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                .setBook(VALID_BOOK_1)
                .build();

        offer.setBook(VALID_BOOK_2);
        offer.setContactInfo(VALID_CONTACT_INFO_2);
        offer.setPrice(VALID_PRICE_2);
        offer.setNumberOfCopies(VALID_NUMBER_OF_COPIES_2);

        Assertions.assertEquals(VALID_BOOK_2, offer.getBook());
        Assertions.assertEquals(VALID_CONTACT_INFO_2, offer.getContactInfo());
        Assertions.assertEquals(VALID_PRICE_2, offer.getPrice());
        Assertions.assertEquals(VALID_NUMBER_OF_COPIES_2, offer.getNumberOfCopies());
    }

    @Test
    public void createOfferWithInvalidPublishingDateThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Offer.Builder()
                        .setPublishingTime(INVALID_PUBLISHING_DATE_FUTURE)
                        .setContactInfo(VALID_CONTACT_INFO_1)
                        .setPrice(VALID_PRICE_1)
                        .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                        .setBook(VALID_BOOK_1)
                        .build()
        );
    }


    @Test
    public void createOfferWithInvalidBookThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Offer.Builder()
                        .setPublishingTime(VALID_PUBLISHING_DATE_1)
                        .setContactInfo(VALID_CONTACT_INFO_1)
                        .setPrice(VALID_PRICE_1)
                        .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                        .setBook(INVALID_BOOK_1)
                        .build()
        );
    }

    @Test
    public void createOfferWithInvalidContactInfoThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Offer.Builder()
                        .setPublishingTime(VALID_PUBLISHING_DATE_1)
                        .setContactInfo(INVALID_CONTACT_INFO)
                        .setPrice(VALID_PRICE_1)
                        .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                        .setBook(VALID_BOOK_1)
                        .build()
        );
    }

    @Test
    public void createOfferWithInvalidNumberOfCopiesThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Offer.Builder()
                        .setPublishingTime(VALID_PUBLISHING_DATE_1)
                        .setContactInfo(VALID_CONTACT_INFO_1)
                        .setPrice(VALID_PRICE_1)
                        .setNumberOfCopies(INVALID_NUMBER_OF_COPIES)
                        .setBook(VALID_BOOK_1)
                        .build()
        );
    }

    @Test
    public void createOfferWithInvalidPriceThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Offer.Builder()
                        .setPublishingTime(VALID_PUBLISHING_DATE_1)
                        .setContactInfo(VALID_CONTACT_INFO_1)
                        .setPrice(INVALID_PRICE_NEGATIVE)
                        .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                        .setBook(VALID_BOOK_1)
                        .build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidNumberOfCopies() {

        Offer offer = new Offer.Builder()
                .setPublishingTime(VALID_PUBLISHING_DATE_1)
                .setContactInfo(VALID_CONTACT_INFO_1)
                .setPrice(VALID_PRICE_1)
                .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                .setBook(VALID_BOOK_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> offer.setNumberOfCopies(INVALID_NUMBER_OF_COPIES)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidPrice() {

        Offer offer = new Offer.Builder()
                .setPublishingTime(VALID_PUBLISHING_DATE_1)
                .setContactInfo(VALID_CONTACT_INFO_1)
                .setPrice(VALID_PRICE_1)
                .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                .setBook(VALID_BOOK_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> offer.setPrice(INVALID_PRICE_NEGATIVE)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidBook() {

        Offer offer = new Offer.Builder()
                .setPublishingTime(VALID_PUBLISHING_DATE_1)
                .setContactInfo(VALID_CONTACT_INFO_1)
                .setPrice(VALID_PRICE_1)
                .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                .setBook(VALID_BOOK_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> offer.setBook(INVALID_BOOK_1)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidContactInfo() {

        Offer offer = new Offer.Builder()
                .setPublishingTime(VALID_PUBLISHING_DATE_1)
                .setContactInfo(VALID_CONTACT_INFO_1)
                .setPrice(VALID_PRICE_1)
                .setNumberOfCopies(VALID_NUMBER_OF_COPIES_1)
                .setBook(VALID_BOOK_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> offer.setContactInfo(INVALID_CONTACT_INFO)
        );
    }



}
