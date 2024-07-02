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

    private static final int MINIMUM_NUMBER_OF_CATEGORIES = 1;

    private static final int MAXIMUM_NUMBER_OF_CATEGORIES = 3;

    protected String name;

    protected int yearOfPublishing;

    protected String description;

    protected List<BookAuthor> bookAuthors = new ArrayList<>();

    protected Set<Category> allCategories;

    protected Set<Category> primarilyCategories;

    protected Set<Image> images = new HashSet<>();

    protected Image previewImage;

    protected String language;

    protected int ageOfBook;

    public Book(String name, int yearOfPublishing, String description,
                Set<Category> categories, Set<Category> primarilyCategories, String language, int ageOfBook) {

        validateName(name);
        validateYearOfPublishing(yearOfPublishing);
        validateDescription(description);
        validateCategories(categories);
        validateLanguage(language);
        validateAgeOfBook(yearOfPublishing);

        this.name = name;
        this.yearOfPublishing = yearOfPublishing;
        this.description = description;
        this.language = language;
        this.ageOfBook = ageOfBook;

        validateAllCategories(categories);
        validatePrimarilyCategories(categories, primarilyCategories);


        this.allCategories = categories;
        this.primarilyCategories = primarilyCategories;

        categories.forEach(category -> category.addAttachedBook(this));
        primarilyCategories.forEach(primarilyCat -> primarilyCat.addBookWhereCategoryIsPrimarily(this));

        records.add(this);
    }

    public void addCategory(Category category) {
        validateCategory(category);
        if (!allCategories.contains(category)) {
            allCategories.add(category);
            category.addAttachedBook(this);
        }
    }

    public void removeCategory(Category category) {
        validateCategory(category);
        if (allCategories.contains(category)) {
            if (allCategories.size() < 2) {
                throw new ValidationException("At least one category should be present");
            }
            allCategories.remove(category);
            category.removeAttachedBook(this);
            if (primarilyCategories.contains(category)) {
                primarilyCategories.remove(category);
                category.removeBookWhereCategoryIsPrimarily(this);
            }
        }
    }

    public void addPrimarilyCategory(Category category) {
        validateCategory(category);
        if (!allCategories.contains(category)) {
            throw new ValidationException("Primarily category should be also present in all categories");
        }
        primarilyCategories.add(category);
        category.addBookWhereCategoryIsPrimarily(this);
    }

    public void removePrimarilyCategory(Category category) {
        if (primarilyCategories.contains(category)) {
            if (primarilyCategories.size() < 2) {
                throw new ValidationException("At least one primarily category should be present");
            }
            validateCategory(category);
            primarilyCategories.remove(category);
            category.removeBookWhereCategoryIsPrimarily(this);
        }
    }

    private static void validateAllCategories(Set<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            throw new ValidationException("Categories can't be null or empty");
        }
    }

    private static void validatePrimarilyCategories(Set<Category> allCategories, Set<Category> primarilyCategories) {
        if (primarilyCategories == null || primarilyCategories.isEmpty()) {
            throw new ValidationException("Primarily categories can't be null or empty");
        }
        if (!allCategories.containsAll(primarilyCategories)) {
            throw new ValidationException("All primarily categories should be present in all categories");
        }
    }

    private void validateCategory(Category category) {
        if (category == null) {
            throw new ValidationException("Category can't be null");
        }
    }

    public void addAuthorBook(BookAuthor bookAuthor) {
        validateBookAuthor(bookAuthor);
            bookAuthors.add(bookAuthor);
    }

    public void deleteAuthorBook(BookAuthor bookAuthor) {
        if (bookAuthors.contains(bookAuthor)) {
            bookAuthors.remove(bookAuthor);
            bookAuthor.removeBookAuthor();
        }
    }

    private void validateBookAuthor(BookAuthor bookAuthor) {
        if (bookAuthor == null) {
            throw new ValidationException("book author can't be null");
        }
        if (bookAuthor.getBook() != this) {
            throw new ValidationException("BookAuthor attach to another book");
        }
        if (bookAuthor.getAuthor() == null) {
            throw new ValidationException("Author should be attached to the author book");
        }

    }

    public void addImage(Image image) {
        validateImage(image);

        if (image == previewImage) {
            throw new ValidationException("Image is already an preview image");
        }

        images.add(image);
        image.setBook(this);
    }

    public void removeImage(Image image) {
        validateImage(image);

        images.remove(image);

        image.setBook(null);
    }

    public void setPreviewImage(Image previewImage) {
        if (this.previewImage != previewImage) {
            if (previewImage != null) {
                if (images.contains(previewImage)) {
                    throw new ValidationException("Preview image already present in all images");
                }
                if (this.previewImage != null) {
                    this.previewImage.setPreview(false);
                    this.previewImage.setBook(null);
                }

                this.previewImage = previewImage;
                previewImage.setBook(this);
                previewImage.setPreview(true);

            } else if (this.previewImage.isPreview()) {
                this.previewImage.setPreview(false);
                this.previewImage = null;
            } else {
                this.previewImage = null;
            }
        }
    }


    private void validateImage(Image image) {
        if (image == null) {
            throw new ValidationException("Image can't be null");
        }
    }

    void cleanAuthorBookFromSet(BookAuthor bookAuthor) {
        bookAuthors.remove(bookAuthor);
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

    public int getAgeOfBook() {
        return ageOfBook;
    }

    public String getLanguage() {
        return language;
    }

    public List<BookAuthor> getBookAuthors() {
        return new ArrayList<>(bookAuthors);
    }

    public Set<Category> getAllCategories() {
        return new HashSet<>(allCategories);
    }

    public Set<Category> getPrimarilyCategories() {
        return new HashSet<>(primarilyCategories);
    }

    public Set<Image> getImages() {
        return new HashSet<>(images);
    }

    public Image getPreviewImage() {
        return previewImage;
    }

    public void setLanguage(String language) {
        validateLanguage(language);
        this.language = language;
    }

    private int calculateAge(int yearOfPublishing) {
        validateYearOfPublishing(yearOfPublishing);
        return LocalDate.now().getYear() - yearOfPublishing;
    }


    private static void validateAgeOfBook(int yearOfPublishing) {
        if (yearOfPublishing > LocalDate.now().getYear()) {
            throw new ValidationException("Age can't be in future year");
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new ValidationException("Description can't be blank or empty");
        }
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
        if (categories == null || categories.size() < 1) {
            throw new ValidationException("At least " + MINIMUM_NUMBER_OF_CATEGORIES + " category(es) should be present");
        }
        if (categories.size() > MAXIMUM_NUMBER_OF_CATEGORIES) {
            throw new ValidationException("The limit of maximum number of categories is " + MAXIMUM_NUMBER_OF_CATEGORIES);
        }
    }

    private static void validateLanguage(String language) {
        if (language == null || language.isBlank()) {
            throw new ValidationException("Language should be present");
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
