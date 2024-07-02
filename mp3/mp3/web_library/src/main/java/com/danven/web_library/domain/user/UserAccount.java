package com.danven.web_library.domain.user;

import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.OfferReport;
import com.danven.web_library.domain.report.Report;
import com.danven.web_library.exceptions.ValidationException;

import java.time.LocalDate;
import java.util.*;

public class UserAccount implements IUserAccount {

    private static final List<UserAccount> records = new ArrayList<>();

    private final String name;

    private final String username;

    private final LocalDate dateOfBirth;

    private final Set<Offer> favouriteOffers = new HashSet<>();

    private final Set<Report> reports = new HashSet<>();

    public UserAccount(String name, String username, LocalDate dateOfBirth) {
        this.name = name;
        this.username = username;
        this.dateOfBirth = dateOfBirth;

        validateName(name);
        validateUsername(username);
        validateDateOfBirth(dateOfBirth);

        records.add(this);
    }

    @Override
    public void createOfferReport(String description, Offer offer) {
        Report report = new OfferReport(
                description, offer, this
        );
        reports.add(report);
    }

    @Override
    public void addFavoriteOffer(Offer offer) {
        favouriteOffers.add(offer);
    }

    @Override
    public void removeFavouriteOffer(Offer offer) {
        favouriteOffers.remove(offer);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name can't be null ot empty");
        }
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidationException("Name can't be null ot empty");
        }
    }

    private void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new ValidationException("Date of birth can't be null or ne in the future");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public Set<Offer> getFavouriteOffers() {
        return new HashSet<>(favouriteOffers);
    }

    @Override
    public Set<Report> getReports() {
        return new HashSet<>(reports);
    }

    public static List<UserAccount> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(name, that.name) && Objects.equals(username, that.username) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(favouriteOffers, that.favouriteOffers) && Objects.equals(reports, that.reports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, username, dateOfBirth, favouriteOffers, reports);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", favouriteOffers=" + favouriteOffers +
                ", reports=" + reports +
                '}';
    }
}
