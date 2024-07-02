package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class DiskBook extends Book implements IDiskBook, Serializable {

    @Serial
    private static final long serialVersionUID = 3L;

    private final static List<DiskBook> records = new ArrayList<>();

    private double durationInHours;

    private DiskFormat diskFormat;

    public DiskBook(String name, int yearOfPublishing, String description, String author, String language,
                    Set<Category> categories, double durationInHours, DiskFormat diskFormat) {

        super(name, yearOfPublishing, description, author, language, categories);

        validateDurationInHours(durationInHours);
        validateDiskFormat(diskFormat);

        this.durationInHours = durationInHours;
        this.diskFormat = diskFormat;

        records.add(this);
    }

    @Override
    public void setDurationInHours(double durationInHours) {
        validateDurationInHours(durationInHours);
        this.durationInHours = durationInHours;
    }

    @Override
    public void setDiskFormat(DiskFormat diskFormat) {
        validateDiskFormat(diskFormat);
        this.diskFormat = diskFormat;
    }

    @Override
    public double getDurationInHours() {
        return durationInHours;
    }

    @Override
    public DiskFormat getDiskFormat() {
        return diskFormat;
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
        DiskBook diskBook = (DiskBook) o;
        return Double.compare(diskBook.durationInHours, durationInHours) == 0 && diskFormat == diskBook.diskFormat;
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
                ", categories=" + categories +
                ", language=" + language +
                ", durationInHours=" + durationInHours +
                ", diskFormat=" + diskFormat +
                '}';
    }


}
