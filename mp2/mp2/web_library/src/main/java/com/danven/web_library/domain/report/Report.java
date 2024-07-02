package com.danven.web_library.domain.report;

import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.user.UserAccount;
import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Report implements Serializable {

    @Serial
    private static final long serialVersionUID = 9L;

    private static final List<Report> records = new ArrayList<>();

    private final String description;
    private final Offer offer;
    private final UserAccount userAccount;

    private Report(Builder builder) {
        validateDescription(builder.description);
        validateOffer(builder.offer);
        validateUserAccount(builder.userAccount);

        this.description = builder.description;
        this.offer = builder.offer;
        this.userAccount = builder.userAccount;

        records.add(this);
    }

    public String getDescription() {
        return description;
    }

    public Offer getOffer() {
        return offer;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public static class Builder {
        private String description;
        private Offer offer;
        private UserAccount userAccount;

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setOffer(Offer offer) {
            this.offer = offer;
            return this;
        }

        public Builder setUserAccount(UserAccount userAccount) {
            this.userAccount = userAccount;
            return this;
        }

        public Report build() {
            return new Report(this);
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.length() < 10) {
            throw new ValidationException("Description is too short or missing");
        }
    }

    private static void validateOffer(Offer offer) {
        if (offer == null) {
            throw new ValidationException("Offer cannot be null");
        }
    }

    private static void validateUserAccount(UserAccount userAccount) {
        if (userAccount == null) {
            throw new ValidationException("User cannot be null");
        }
    }


    public static List<Report> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(description, report.description) && Objects.equals(offer, report.offer) && Objects.equals(userAccount, report.userAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, offer, userAccount);
    }

    @Override
    public String toString() {
        return "Report{" +
                "description='" + description + '\'' +
                ", offer=" + offer +
                ", userAccount=" + userAccount +
                '}';
    }
}
