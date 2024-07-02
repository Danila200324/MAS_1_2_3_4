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

    protected String language;

    protected Set<Category> categories = new HashSet<>();

    protected Book() {
    }

    public Book(String name, int yearOfPublishing, String description, String author, String language, Set<Category> categories) {

        validateName(name);
        validateYearOfPublishing(yearOfPublishing);
        validateDescription(description);
        validateAuthor(author);
        validateLanguage(language);
        validateCategories(categories);

        this.name = name;
        this.yearOfPublishing = yearOfPublishing;
        this.description = description;
        this.author = author;
        this.language = language;
        this.categories = categories;

        records.add(this);
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        validateYearOfPublishing(yearOfPublishing);
        this.yearOfPublishing = yearOfPublishing;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    public void setAuthor(String author) {
        validateAuthor(author);
        this.author = author;
    }

    public void setLanguage(String language) {
        validateLanguage(language);
        this.language = language;
    }

    public void setCategories(Set<Category> categories) {
        validateCategories(categories);
        this.categories = categories;
    }

    private void validateName(String name){
        if(name == null || name.isBlank()){
            throw new ValidationException("Name can't be null or empty");
        }
    }

    private void validateYearOfPublishing(int yearOfPublishing){
        if(LocalDate.now().getYear() < yearOfPublishing){
            throw new ValidationException("The date os publishing should be in the past");
        }
    }

    private void validateDescription(String description){
        if(description == null || description.isBlank()){
            throw new ValidationException("Description can't be null or empty");
        }
    }

    private void validateAuthor(String author){
        if(author == null || author.isBlank()){
            throw new ValidationException("Author can't be null or empty");
        }
    }

    private void validateLanguage(String language){
        if(language == null || language.isBlank()){
            throw new ValidationException("Language can't be null or empty");
        }
    }

    private void validateCategories(Set<Category> categories){
        if(categories == null || categories.isEmpty()){
            throw new ValidationException("Categories can't be null or empty");
        }
    }

    public String getName() {
        return name;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public static void removeBook(Book book){
        records.remove(book);
    }

    public static List<Book> getAllRecords(){
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return yearOfPublishing == book.yearOfPublishing && Objects.equals(name, book.name) && Objects.equals(description, book.description) && Objects.equals(author, book.author) && Objects.equals(language, book.language) && Objects.equals(categories, book.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfPublishing, description, author, language, categories);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                ", categories=" + categories +
                '}';
    }
}
