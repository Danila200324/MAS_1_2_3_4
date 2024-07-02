package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DBook extends Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;

    private final static List<DBook> records = new ArrayList<>();

    private double durationInHours;

    private DiskFormat diskFormat;

    protected DBook() {
        super();
    }

    private DBook(BookBuilder bookBuilder) {
        super(bookBuilder);

        validateDurationInHours(bookBuilder.durationInHours);
        validateDiskFormat(bookBuilder.diskFormat);

        durationInHours = bookBuilder.durationInHours;
        diskFormat = bookBuilder.diskFormat;

        records.add(this);
    }

    public static class BookBuilder extends Book.BookBuilder<BookBuilder> {
        private double durationInHours;
        private DiskFormat diskFormat;

        public BookBuilder() {
        }

        public BookBuilder setDurationInHours(double durationInHours) {
            this.durationInHours = durationInHours;
            return this;
        }

        public BookBuilder setDiskFormat(DiskFormat diskFormat) {
            this.diskFormat = diskFormat;
            return this;
        }

        @Override
        public DBook build() {
            return new DBook(this);
        }

        @Override
        protected BookBuilder self() {
            return this;
        }

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
                ", categories=" + categories +
                ", images=" + images +
                ", language=" + language +
                ", durationInHours=" + durationInHours +
                ", diskFormat=" + diskFormat +
                '}';
    }
}
