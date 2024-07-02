package com.danven.web_library;


import com.danven.web_library.domain.address.Address;
import com.danven.web_library.domain.book.*;

import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.Report;
import com.danven.web_library.domain.user.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Factory {

    public static Image getImage(String path, String format) {
        Image image = null;
        try {
            image = new Image.ImageBuilder()
                    .setImage(Files.readAllBytes(Path.of(path)))
                    .setFormat(format)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Category getCategory(String name, String description) {
        return new Category.CategoryBuilder()
                .setName(name)
                .setDescription(description)
                .build();
    }

    public static Language getLanguage(String name, String acronym) {
        return new Language.LanguageBuilder()
                .setName(name)
                .setAcronym(acronym)
                .build();
    }

    public static PBook getPBookWithAuthorAndDefaultParameters(String name) {

        Set<Category> categories = new HashSet<>(Set.of(
                getCategory("Science", "Science books..."),
                getCategory("Fantasy", "Fantasy Book")
        ));

        Set<Image> images = new HashSet<>(Set.of(
                getImage("src/test/testData/book1(jpeg).jpeg", "jpeg"),
                getImage("src/test/testData/book2(jpeg).jpeg", "jpeg")
        ));

        Language language = getLanguage("English", "EN");

        return new PBook.BookBuilder()
                .setCategories(categories)
                .setLanguage(language)
                .setName(name)
                .setDescription("Description for book 1")
                .setYearOfPublishing(2012)
                .setNumberOfPages(5)
                .build();

    }

    public static DBook getDBookWithAuthorAndDefaultParameters(String name) {

        Set<Category> categories = new HashSet<>(Set.of(
                getCategory("Science", "Science books..."),
                getCategory("Fantasy", "Fantasy Book")
        ));

        Set<Image> images = new HashSet<>(Set.of(
                getImage("src/test/testData/book1(jpeg).jpeg", "jpeg"),
                getImage("src/test/testData/book2(jpeg).jpeg", "jpeg")
        ));

        Language language = getLanguage("English", "EN");

        return new DBook.BookBuilder()
                .setCategories(categories)
                .setLanguage(language)
                .setName(name)
                .setDescription("Description for book 1")
                .setYearOfPublishing(2012)
                .setDiskFormat(DiskFormat.CD)
                .setDurationInHours(5)
                .build();

    }

    public static ContactInfo getContactInfoWithEmailAndDefaultParameters(String email) {
        return new ContactInfo.Builder()
                .setEmail(email)
                .setTelephoneNumber("11111111")
                .setSocialMediaLink("https://instagram.com/page/sdafsadfsdaf")
                .build();
    }

    public static Offer getOfferWithNumberOfCopiesAndDefaultParameters(int numberOfCopies) {
        return new Offer.Builder()
                .setPublishingTime(
                        LocalDateTime.of(2024, 1, 22, 9, 5)
                )
                .setContactInfo(
                        Factory.getContactInfoWithEmailAndDefaultParameters("danven2018@gmail.com")
                )
                .setPrice(50)
                .setNumberOfCopies(numberOfCopies)
                .setBook(
                        Factory.getPBookWithAuthorAndDefaultParameters("Book 1")
                )
                .build();
    }

    public static User getUserWithUsernameAndDefaultParameters(String username) {
        return new User.UserBuilder()
                .setName("User 1")
                .setUsername(username)
                .setDateOfBirth(LocalDate.of(2003, 9, 9))
                .setUserAccount(getUserAccountWithEmailAndDefaultParameters(
                        "danven2018@mail.ru"
                ))
                .build();
    }

    public static UserAccount getUserAccountWithEmailAndDefaultParameters(String email) {

        Set<Offer> ownedOffers = new HashSet<>();
        ownedOffers.add(
                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(1)
        );

        ownedOffers.add(
                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(2)
        );

        Set<Offer> favouriteOffers = new HashSet<>(ownedOffers);

        return new UserAccount.UserAccountBuilder()
                .setDateOfRegistration(
                        LocalDateTime.of(2022, 12, 12, 12, 12)
                )
                .setEnabled(true)
                .setRole(Role.USER)
                .setOwnedOffers(
                        ownedOffers
                )
                .setFavouriteOffers(
                        favouriteOffers
                )
                .setEmail(
                        email
                )
                .setPassword(
                        "Danven2018"
                )
                .build();
    }

    public static Report getReportWithDescriptionAndDefaultValues(String description) {
        return new Report.Builder()
                .setDescription(description)
                .setOffer(getOfferWithNumberOfCopiesAndDefaultParameters(1))
                .setUserAccount(getUserAccountWithEmailAndDefaultParameters("danven@gmail.com"))
                .build();
    }

    public static Address getAddressWithCityAndDefaultValues(String city) {
        return new Address.AddressBuilder()
                .setCountry("Poland")
                .setCity(city)
                .setHouseNumber("5r")
                .setPostalCode("4567")
                .build();
    }

    public static Address getAddressWithCityAndPostalCodeAndDefaultValues(String city, String postalCode) {
        return new Address.AddressBuilder()
                .setCountry("Poland")
                .setCity(city)
                .setHouseNumber("5r")
                .setPostalCode(postalCode)
                .build();
    }

    public static Author getAuthorByFullName(String fullName) {
        return new Author.AuthorBuilder().setFullName(fullName).build();
    }


}
