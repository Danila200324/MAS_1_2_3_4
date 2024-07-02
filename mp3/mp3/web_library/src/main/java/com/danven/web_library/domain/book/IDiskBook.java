package com.danven.web_library.domain.book;

public interface IDiskBook {

    void setDurationInHours(double durationInHours);

    void setDiskFormat(DiskFormat diskFormat);

    double getDurationInHours();

    DiskFormat getDiskFormat();

}
