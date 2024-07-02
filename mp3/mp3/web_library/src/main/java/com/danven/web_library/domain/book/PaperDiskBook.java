package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.util.*;

public class PaperDiskBook extends PaperBook implements IDiskBook{

    private static final List<PaperDiskBook> records = new ArrayList<>();

    private double durationInHours;

    private DiskFormat diskFormat;

    public PaperDiskBook(String name, int yearOfPublishing, String description, String author, String language,
                         Set<Category> categories, int numberOfPages, double durationInHours, DiskFormat diskFormat) {
        super(name, yearOfPublishing, description, author, language, categories, numberOfPages);

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

    public void validateDurationInHours(double durationInHours){
        if(durationInHours <= 0){
            throw new ValidationException("Duration in hours can't be below zero");
        }
    }

    public void validateDiskFormat(DiskFormat diskFormat){
        if(diskFormat == null){
            throw new ValidationException("Disk format can't be null");
        }
    }

    public static List<Book> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaperDiskBook that = (PaperDiskBook) o;
        return Double.compare(that.durationInHours, durationInHours) == 0 && diskFormat == that.diskFormat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), durationInHours, diskFormat);
    }

    @Override
    public String toString() {
        return "PaperDiskBook{" +
                "durationInHours=" + durationInHours +
                ", diskFormat=" + diskFormat +
                '}';
    }
}
