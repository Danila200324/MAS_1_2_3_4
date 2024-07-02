package com.danven.web_library.service.offer_service;

import java.util.List;

public interface ViewModeService {

    List<?> getOffers();

    List<?> getOffersByBookName(String bookName);

}
