package com.danven.web_library.domain.offer;

import com.danven.web_library.domain.book.Book;
import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private Offer(Builder builder) {

        validatePublishingTime(builder.publishingTime);
        validatePrice(builder.price);
        validateNumberOfCopies(builder.numberOfCopies);
        validateBook(builder.book);
        validateContactInfo(builder.contactInfo);

        this.publishingTime = builder.publishingTime;
        this.price = builder.price;
        this.numberOfCopies = builder.numberOfCopies;
        this.book = builder.book;
        this.contactInfo = builder.contactInfo;

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

    public static class Builder {
        private LocalDateTime publishingTime;
        private double price;
        private int numberOfCopies;
        private Book book;
        private ContactInfo contactInfo;

        public Builder setPublishingTime(LocalDateTime publishingTime) {
            this.publishingTime = publishingTime;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setNumberOfCopies(int numberOfCopies) {
            this.numberOfCopies = numberOfCopies;
            return this;
        }

        public Builder setBook(Book book) {
            this.book = book;
            return this;
        }

        public Builder setContactInfo(ContactInfo contactInfo) {
            this.contactInfo = contactInfo;
            return this;
        }

        public Offer build() {
            return new Offer(this);
        }
    }

    public static List<Offer> getRecords() {
        return records;
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
