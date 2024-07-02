package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class DBook extends Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;

    private final static List<DBook> records = new ArrayList<>();

    private double durationInHours;

    private DiskFormat diskFormat;

    public DBook(String name, int yearOfPublishing, String description,  Set<Category> categories, Set<Category> primarilyCategories, String language, int ageOfBook, double durationInHours, DiskFormat diskFormat) {
        super(name, yearOfPublishing, description, categories, primarilyCategories, language, ageOfBook);

        validateDurationInHours(durationInHours);
        validateDiskFormat(diskFormat);

        this.durationInHours = durationInHours;
        this.diskFormat = diskFormat;

        records.add(this);
    }

    public double getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(double durationInHours) {
        validateDurationInHours(durationInHours);
        this.durationInHours = durationInHours;
    }

    public DiskFormat getDiskFormat() {
        return diskFormat;
    }

    public void setDiskFormat(DiskFormat diskFormat) {
        validateDiskFormat(diskFormat);
        this.diskFormat = diskFormat;
    }

    private static void validateDurationInHours(double durationInHours) {
        if (durationInHours <= 0) {
            throw new ValidationException("Duration of the books should be greater then zero");
        }
    }

    private static void validateDiskFormat(DiskFormat diskFormat) {
        if (diskFormat == null) {
            throw new ValidationException("Disk format should be present");
        }
    }


    public static List<Book> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBook dBook = (DBook) o;
        return Double.compare(dBook.durationInHours, durationInHours) == 0 && diskFormat == dBook.diskFormat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(durationInHours, diskFormat);
    }

    @Override
    public String toString() {
        return "DBook{" +
                "name='" + name + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                ", description='" + description + '\'' +
                ", categories=" + allCategories +
                ", language=" + language +
                ", durationInHours=" + durationInHours +
                ", diskFormat=" + diskFormat +
                '}';
    }
}
