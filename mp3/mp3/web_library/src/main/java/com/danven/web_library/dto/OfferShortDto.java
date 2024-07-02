package com.danven.web_library.dto;

public class OfferShortDto {

    private double price;

    private String bookName;

    private String language;

    public OfferShortDto(double price, String bookName, String language) {
        this.price = price;
        this.bookName = bookName;
        this.language = language;
    }

}
