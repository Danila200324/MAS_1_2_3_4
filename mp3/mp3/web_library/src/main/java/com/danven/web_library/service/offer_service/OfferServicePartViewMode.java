package com.danven.web_library.service.offer_service;

import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.dto.OfferShortDto;

import java.util.List;

public class OfferServicePartViewMode implements ViewModeService {

    @Override
    public List<OfferShortDto> getOffers() {
        return Offer.getRecords().stream().map(offer ->
                new OfferShortDto(
                        offer.getPrice(),
                        offer.getBook().getName(),
                        offer.getBook().getLanguage()
                )
        ).toList();
    }

    @Override
    public List<OfferShortDto> getOffersByBookName(String bookName) {
        return Offer.getRecords().stream().
                filter(offer -> offer.getBook().getName().equals(bookName))
                .map(offer ->
                        new OfferShortDto(
                                offer.getPrice(),
                                offer.getBook().getName(),
                                offer.getBook().getLanguage()
                        )
                ).toList();
    }
}
