package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public abstract class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final List<Book> records = new ArrayList<>();

    protected String name;

    protected int yearOfPublishing;

    protected String description;

    protected String author;

    protected Set<Category> categories;

    protected Set<Image> images;

    protected Language language;

    protected int ageOfBook;

    protected Book() {
    }

    protected Book(BookBuilder<?> bookBuilder) {
        validateName(bookBuilder.name);
        validateYearOfPublishing(bookBuilder.yearOfPublishing);
        validateAuthor(bookBuilder.author);
        validateCategories(bookBuilder.categories);
        validateLanguage(bookBuilder.language);
        validateImages(bookBuilder.images);

        this.name = bookBuilder.name;
        this.yearOfPublishing = bookBuilder.yearOfPublishing;
        this.description = bookBuilder.description;
        this.author = bookBuilder.author;
        this.categories = bookBuilder.categories;
        this.language = bookBuilder.language;
        this.images = bookBuilder.images;
        this.ageOfBook = calculateAge(bookBuilder.yearOfPublishing);

        records.add(this);
    }

    protected abstract static class BookBuilder<T extends BookBuilder<T>> {
        private String name;
        private int yearOfPublishing;
        private String description;
        private String author;
        private Set<Category> categories;
        private Language language;
        private Set<Image> images;

        protected abstract T self();

        public T setName(String name) {
            this.name = name;
            return self();
        }

        public T setYearOfPublishing(int yearOfPublishing) {
            this.yearOfPublishing = yearOfPublishing;
            return self();
        }

        public T setDescription(String description) {
            this.description = description;
            return self();
        }

        public T setAuthor(String author) {
            this.author = author;
            return self();
        }

        public T setCategories(Set<Category> categories) {
            this.categories = categories;
            return self();
        }

        public T setLanguage(Language language) {
            this.language = language;
            return self();
        }

        public T setImages(Set<Image> images) {
            this.images = images;
            return self();
        }

        protected abstract Book build();
    }

    public void addCategory(Category category) {
        validateCategory(category);
        categories.add(category);
    }

    public void deleteCategory(Category category) {
        validateDeletingCategoryFromCollection(category);
        categories.remove(category);
    }

    public void addImage(Image image) {
        validateImage(image);
        images.add(image);
    }

    public void deleteImage(Image image) {
        validateDeletingImageFromCollection(image);
        images.remove(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        validateYearOfPublishing(yearOfPublishing);
        this.yearOfPublishing = yearOfPublishing;
        this.ageOfBook = calculateAge(yearOfPublishing);
    }

    public void setYearOfPublishing(LocalDate dateOfPublishing) {
        int yearOfPublishing = dateOfPublishing.getYear();
        validateYearOfPublishing(yearOfPublishing);
        this.yearOfPublishing = yearOfPublishing;
        this.ageOfBook = calculateAge(yearOfPublishing);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        validateAuthor(author);
        this.author = author;
    }

    public int getAgeOfBook() {
        return ageOfBook;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void setCategories(Set<Category> categories) {
        validateCategories(categories);
        this.categories = categories;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        validateLanguage(language);
        this.language = language;
    }

    public Set<Image> getImages() {
        return Collections.unmodifiableSet(images);
    }

    public void setImages(Set<Image> images) {
        validateImages(images);
        this.images = images;
    }

    private int calculateAge(int yearOfPublishing){
        validateYearOfPublishing(yearOfPublishing);
        return LocalDate.now().getYear() - yearOfPublishing;
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name of the book is invalid");
        }
    }

    private static void validateAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new ValidationException("Description of the book is invalid");
        }
    }

    private static void validateYearOfPublishing(int yearOfPublishing) {
        if (yearOfPublishing > LocalDate.now().getYear()) {
            throw new ValidationException("Year of publishing should not be greater then current year");
        }
    }

    private static void validateCategories(Set<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            throw new ValidationException("At least one category should be present");
        }
    }

    private static void validateImages(Set<Image> images) {
        if (images == null || images.isEmpty()) {
            throw new ValidationException("At least one category should be present");
        }
    }

    private static void validateLanguage(Language language) {
        if (language == null) {
            throw new ValidationException("Language should be present");
        }
    }

    private void validateImage(Image image) {
        if (image == null) {
            throw new ValidationException("Image should be present");
        }
    }

    private void validateCategory(Category category) {
        if (category == null) {
            throw new ValidationException("Category should be present");
        }
    }

    private void validateDeletingCategoryFromCollection(Category category) {
        validateCategory(category);
        if (categories.size() <= 1) {
            throw new ValidationException("At least one category should be present");
        }
    }

    private void validateDeletingImageFromCollection(Image image) {
        validateImage(image);
        if (images.size() <= 1) {
            throw new ValidationException("At least one image should be present");
        }
    }

    public static List<Book> getRecords() {
        return Collections.unmodifiableList(records);
    }

    public static List<Book> getRecords(int numberOfRecords) {
        if (numberOfRecords < 0) {
            throw new IllegalArgumentException("Number of records cannot be negative.");
        }
        if (numberOfRecords >= records.size()) {
            return Collections.unmodifiableList(records);
        }
        return Collections.unmodifiableList(records.subList(0, numberOfRecords));
    }

}
