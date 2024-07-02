package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Image implements Serializable {

    @Serial
    private static final long serialVersionUID = 4L;

    private static final List<Image> records = new ArrayList<>();

    private byte[] image;

    private String format;

    //basic association many to one
    private Book book;

    private Image(ImageBuilder imageBuilder) {
        validateImage(imageBuilder.image);
        validateFormat(imageBuilder.format);

        this.image = imageBuilder.image;
        this.format = imageBuilder.format;
        setBook(imageBuilder.book);

        records.add(this);
    }

    public void setImage(byte[] image) {
        validateImage(image);
        this.image = image;
    }

    public void setFormat(String format) {
        validateFormat(format);
        this.format = format;
    }

    public void setBook(Book book) {
        if (this.book != null) {
            if (book == null) {
                this.book.deleteImage(this);
            } else if (!book.equals(this.book)) {
                this.book.deleteImage(this);
                book.addImage(this);
            }
        } else if (book != null) {
            book.addImage(this);
        }
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public byte[] getImage() {
        return image;
    }

    public String getFormat() {
        return format;
    }


    public static class ImageBuilder {
        private byte[] image;
        private String format;
        private Book book;

        public ImageBuilder setImage(byte[] image) {
            this.image = image;
            return this;
        }

        public ImageBuilder setFormat(String format) {
            this.format = format;
            return this;
        }

        public ImageBuilder setBook(Book book) {
            this.book = book;
            return this;
        }

        public Image build() {
            return new Image(this);
        }
    }


    private static void validateImage(byte[] image) {
        if (image == null || image.length == 0) {
            throw new ValidationException("Image data is invalid");
        }
    }

    private static void validateFormat(String format) {
        if (format == null || format.isBlank()) {
            throw new ValidationException("Format is invalid");
        }
    }

    public static List<Image> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image1 = (Image) o;
        return Arrays.equals(image, image1.image) && Objects.equals(format, image1.format);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(format);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "image=" +
                ", format='" + format + '\'' +
                '}';
    }
}
