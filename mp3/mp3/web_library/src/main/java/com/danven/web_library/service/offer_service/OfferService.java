package com.danven.web_library.service.offer_service;

import com.danven.web_library.exceptions.ValidationException;

import java.util.List;

public class OfferService {

    private ViewModeService viewModeService;

    public OfferService(ViewModeService viewModeService) {

        validateViewModeService(viewModeService);

        this.viewModeService = viewModeService;
    }

    public void setViewModeService(ViewModeService viewModeService) {

        validateViewModeService(viewModeService);

        this.viewModeService = viewModeService;
    }

    private void validateViewModeService(ViewModeService viewModeService){
        if (viewModeService == null){
            throw new ValidationException("View mode service can't be null");
        }
    }

    public List<?> getOffers() {
        return viewModeService.getOffers();
    }

    public List<?> getOffersByBookName(String bookName) {
        return viewModeService.getOffersByBookName(bookName);
    }

}
