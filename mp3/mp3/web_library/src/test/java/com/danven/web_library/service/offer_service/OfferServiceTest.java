package com.danven.web_library.service.offer_service;

import com.danven.web_library.RecordsUtility;
import com.danven.web_library.domain.book.Category;
import com.danven.web_library.domain.book.PaperBook;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.dto.OfferShortDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class OfferServiceTest {

    private Offer offer1;

    private List<Offer> offersFull;

    private List<OfferShortDto> offersShort;

    @BeforeEach
    public void setup() {
        RecordsUtility.clearRecords();

        offer1 = createOffer("Book1");
        Offer offer2 = createOffer("Book2");
        Offer offer3 = createOffer("Book3");

        offersFull = List.of(offer1, offer2, offer3);

        offersShort = Stream.of(offer1, offer2, offer3).
                map(offer ->
                        new OfferShortDto(
                                offer.getPrice(),
                                offer.getBook().getName(),
                                offer.getBook().getLanguage()
                        )
                ).toList();
    }

    private Offer createOffer(String bookName) {
        PaperBook book = new PaperBook(bookName, 2015, "Description for book 1", "Book author 1", "English",
                Set.of(new Category("Category 1", "Description for category 1")), 2);
        ContactInfo contactInfo = new ContactInfo("username@gmail.com", "35786456", "https://instagram.com/5647");
        return new Offer(LocalDateTime.now(), 12.3, 3, book, contactInfo);
    }


    @Test
    public void testOfferServiceFullMode() {
        OfferServiceFullViewMode offerServiceFullViewMode =
                Mockito.mock(OfferServiceFullViewMode.class);

        Mockito.when(offerServiceFullViewMode.getOffers()).thenReturn(offersFull);

        String searchWord = "Book1";

        Mockito.when(offerServiceFullViewMode.
                        getOffersByBookName(searchWord)).
                thenReturn(List.of(offer1));

        OfferService offerService = new OfferService(offerServiceFullViewMode);

        Assertions.assertIterableEquals(offersFull, offerService.getOffers());
        Assertions.assertIterableEquals(List.of(offer1), offerService.getOffersByBookName(searchWord));
    }

    @Test
    public void testOfferServicePartMode() {
        OfferServicePartViewMode offerServicePartViewMode =
                Mockito.mock(OfferServicePartViewMode.class);

        Mockito.when(offerServicePartViewMode.getOffers()).thenReturn(offersShort);

        String searchWord = "Book1";

        OfferShortDto offerShortDto = new OfferShortDto(offer1.getPrice(), offer1.getBook().getName(),
                offer1.getBook().getDescription());

        Mockito.when(offerServicePartViewMode.
                        getOffersByBookName(searchWord)).
                thenReturn(List.of(offerShortDto));

        OfferService offerService = new OfferService(offerServicePartViewMode);

        Assertions.assertIterableEquals(offersShort, offerService.getOffers());
        Assertions.assertIterableEquals(List.of(offerShortDto), offerService.getOffersByBookName(searchWord));
    }

    @Test
    public void testChangeMode() {
        OfferServiceFullViewMode offerServiceFullViewMode =
                Mockito.mock(OfferServiceFullViewMode.class);
        Mockito.when(offerServiceFullViewMode.getOffers()).thenReturn(offersFull);

        OfferServicePartViewMode offerServicePartViewMode =
                Mockito.mock(OfferServicePartViewMode.class);
        Mockito.when(offerServicePartViewMode.getOffers()).thenReturn(offersShort);

        OfferService offerService = new OfferService(offerServiceFullViewMode);
        Assertions.assertEquals(offersFull, offerService.getOffers());

        offerService.setViewModeService(offerServicePartViewMode);
        Assertions.assertEquals(offersShort, offerService.getOffers());
    }

}
