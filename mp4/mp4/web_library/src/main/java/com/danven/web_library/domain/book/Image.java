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

    private Book book;

    private boolean isPreview;

    public Image(byte[] image, String format) {
        this.image = image;
        this.format = format;
    }

    public void setBook(Book book) {
        if (book != null) {
            if (this.book != book && this.book != null) {
                if (this.book.getImages().contains(this)) {
                    this.book.removeImage(this);
                }

                if (this.book.getPreviewImage() == this) {
                    this.book.setPreviewImage(null);
                }
            }
        }
        this.book = book;
    }

    public void setPreview(boolean preview) {
        if (preview) {
            if (this.book != null && this.book.getPreviewImage() != this) {
                this.isPreview = true;
                this.book.setPreviewImage(this);
            }
        } else {
            if (this.book != null && this.book.getPreviewImage() == this) {
                this.isPreview = false;
                this.book.setPreviewImage(null);
            }
        }
        this.isPreview = preview;

    }

    public void setImage(byte[] image) {
        validateImage(image);
        this.image = image;
    }

    public boolean isPreview() {
        return isPreview;
    }

    public void setFormat(String format) {
        validateFormat(format);
        this.format = format;
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
