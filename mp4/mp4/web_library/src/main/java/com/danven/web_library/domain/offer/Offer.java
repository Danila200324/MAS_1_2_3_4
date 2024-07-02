package com.danven.web_library.domain.offer;

import com.danven.web_library.domain.book.Book;
import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Offer implements Serializable {
    @Serial
    private static final long serialVersionUID = 8L;

    private static final List<Offer> records = new ArrayList<>();

    private final LocalDateTime publishingTime;

    private double price;

    private int numberOfCopies;

    private Book book;

    private ContactInfo contactInfo;

    public Offer(LocalDateTime publishingTime, double price, int numberOfCopies, Book book, ContactInfo contactInfo) {

        validatePublishingTime(publishingTime);
        validatePrice(price);
        validateNumberOfCopies(numberOfCopies);
        validateBook(book);
        validateContactInfo(contactInfo);

        this.publishingTime = publishingTime;
        this.price = price;
        this.numberOfCopies = numberOfCopies;
        this.book = book;
        this.contactInfo = contactInfo;

        records.add(this);
    }

    public LocalDateTime getPublishingTime() {
        return publishingTime;
    }

    public double getPrice() {
        return price;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public Book getBook() {
        return book;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setPrice(double price) {
        validatePrice(price);
        this.price = price;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        validateNumberOfCopies(numberOfCopies);
        this.numberOfCopies = numberOfCopies;
    }

    public void setBook(Book book) {
        validateBook(book);
        this.book = book;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        validateContactInfo(contactInfo);
        this.contactInfo = contactInfo;
    }

    private static void validatePublishingTime(LocalDateTime publishingTime) {
        if (publishingTime == null || publishingTime.isAfter(LocalDateTime.now())) {
            throw new ValidationException("Publishing time cannot be null or in the future.");
        }
    }

    private static void validatePrice(double price) {
        if (price < 0) {
            throw new ValidationException("Price cannot be negative.");
        }
    }

    private static void validateNumberOfCopies(int numberOfCopies) {
        if (numberOfCopies <= 0) {
            throw new ValidationException("Number of copies must be positive.");
        }


    }

    private static void validateBook(Book book) {
        if (book == null) {
            throw new ValidationException("Book cannot be null.");
        }
    }

    private static void validateContactInfo(ContactInfo contactInfo) {
        if (contactInfo == null) {
            throw new ValidationException("Contact information cannot be null.");
        }
    }

    public static List<Offer> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Double.compare(offer.price, price) == 0 && numberOfCopies == offer.numberOfCopies && Objects.equals(publishingTime, offer.publishingTime) && Objects.equals(book, offer.book) && Objects.equals(contactInfo, offer.contactInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publishingTime, price, numberOfCopies, book, contactInfo);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "publishingTime=" + publishingTime +
                ", price=" + price +
                ", numberOfCopies=" + numberOfCopies +
                ", book=" + book +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
