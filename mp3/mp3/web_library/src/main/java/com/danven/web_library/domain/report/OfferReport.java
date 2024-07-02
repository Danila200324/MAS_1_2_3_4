package com.danven.web_library.domain.report;

import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.user.UserAccount;
import com.danven.web_library.exceptions.ValidationException;
import com.danven.web_library.persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OfferReport extends Report {

    private static final List<OfferReport> records = new ArrayList<>();

    private final Offer offer;

    public OfferReport(String description, Offer offer, UserAccount userAccount) {
        super(description, userAccount);

        validateOffer(offer);

        this.offer = offer;

        records.add(this);
    }

    @Override
    public void reactOnReport() {
        Offer.removeOffer(offer);
        PersistenceManager.saveOfferReport(this);
    }

    private static void validateOffer(Offer offer) {
        if (offer == null) {
            throw new ValidationException("Offer cannot be null");
        }
    }

    public Offer getOffer() {
        return offer;
    }

    public static List<Report> getRecords(){
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OfferReport that = (OfferReport) o;
        return Objects.equals(offer, that.offer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offer);
    }

    @Override
    public String toString() {
        return "OfferReport{" +
                "offer=" + offer +
                '}';
    }
}
