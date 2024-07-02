package com.danven.web_library.service.offer_service;

import com.danven.web_library.domain.offer.Offer;

import java.util.List;

public class OfferServiceFullViewMode implements ViewModeService {

    @Override
    public List<Offer> getOffers() {
        return Offer.getRecords();
    }

    @Override
    public List<Offer> getOffersByBookName(String bookName) {
        return Offer.getRecords().stream().
                filter(offer -> offer.getBook().getName().equals(bookName))
                .toList();
    }
}
