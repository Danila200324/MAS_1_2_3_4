package com.danven.web_library.domain.user;

import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.Report;

import java.time.LocalDate;
import java.util.Set;

public interface IUserAccount {

    void createOfferReport(String description, Offer offer);

    void addFavoriteOffer(Offer offer);

    void removeFavouriteOffer(Offer offer);

    String getName();

    String getUsername();

    LocalDate getDateOfBirth();

    Set<Offer> getFavouriteOffers();

    Set<Report> getReports();

}
