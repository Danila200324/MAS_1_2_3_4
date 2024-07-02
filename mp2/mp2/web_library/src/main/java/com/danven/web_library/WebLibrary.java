package com.danven.web_library;

import com.danven.web_library.domain.book.*;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.Report;
import com.danven.web_library.domain.address.Address;
import com.danven.web_library.domain.user.Role;
import com.danven.web_library.domain.user.User;
import com.danven.web_library.domain.user.UserAccount;
import com.danven.web_library.persistence.PersistenceManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class WebLibrary {
    public static void main(String[] args) {
        Category categoryScience = new Category.CategoryBuilder()
                .setName("Science")
                .setDescription("Science books are books that...")
                .build();
        Category categoryDrama = new Category.CategoryBuilder()
                .setName("Drama")
                .setDescription("Drama books are books that...")
                .build();

        Language languageEnglish = new Language.LanguageBuilder()
                .setName("English")
                .setAcronym("EN")
                .build();

        Language languagePolish = new Language.LanguageBuilder()
                .setName("Polish")
                .setAcronym("PL")
                .build();


        byte[] fileFirst = null;

        byte[] fileSecond = null;

        byte[] fileThird = null;

        byte[] fileFourth = null;

        try {
            fileFirst = Files.readAllBytes(Path.of("src/main/resources/images/book1(jpeg).jpeg"));
            fileSecond = Files.readAllBytes(Path.of("src/main/resources/images/book2(jpeg).jpeg"));
            fileThird = Files.readAllBytes(Path.of("src/main/resources/images/book3(jpeg).jpeg"));
            fileFourth = Files.readAllBytes(Path.of("src/main/resources/images/book4(jpeg).jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image imageFirst = new Image.ImageBuilder()
                .setImage(fileFirst)
                .setFormat("jpeg")
                .build();
        Image imageSecond = new Image.ImageBuilder()
                .setImage(fileSecond)
                .setFormat("jpeg")
                .build();
        Image imageThird = new Image.ImageBuilder()
                .setImage(fileThird)
                .setFormat("jpeg")
                .build();
        Image imageFourth = new Image.ImageBuilder()
                .setImage(fileFourth)
                .setFormat("jpeg")
                .build();

        Set<Image>  imagesSetFirst = new HashSet<>();
        imagesSetFirst.add(imageFirst);
        imagesSetFirst.add(imageSecond);

        Set<Image>  imagesSetSecond = new HashSet<>();
        imagesSetSecond.add(imageThird);
        imagesSetSecond.add(imageFourth);

        Set<Category> categorySet = new HashSet<>();
        categorySet.add(categoryScience);
        categorySet.add(categoryDrama);

        PBook pBook = new PBook.BookBuilder()
                .setName("Book 1")
                .setDescription("Description for book 1")
                .setNumberOfPages(500)
                .setYearOfPublishing(2020)
                .setCategories(categorySet)
                .setLanguage(languageEnglish)
                .build();

        DBook dBook = new DBook.BookBuilder()
                .setName("Book 2")
                .setDescription("Description for book 2")
                .setYearOfPublishing(2020)
                .setCategories(categorySet)
                .setLanguage(languagePolish)
                .setDurationInHours(50)
                .setDiskFormat(DiskFormat.CD)
                .build();

        Address address = new Address.AddressBuilder()
                .setCountry("Poland")
                .setCity("Warsaw")
                .setHouseNumber("102r")
                .setPostalCode("4567")
                .build();

        ContactInfo contactInfo = new ContactInfo.Builder()
                .setTelephoneNumber("+12342134")
                .setEmail("username1@gmail.com")
                .setSocialMediaLink("https://instagra.com/vjdfljlsd")
                .build();

        Offer offerFirst = new Offer.Builder()
                .setBook(pBook)
                .setNumberOfCopies(20)
                .setPrice(20)
                .setPublishingTime(LocalDateTime.of(2022,12,12,12,12))
                .setContactInfo(contactInfo)
                .build();

        Offer offerSecond = new Offer.Builder()
                .setBook(dBook)
                .setNumberOfCopies(5)
                .setPrice(67)
                .setPublishingTime(LocalDateTime.of(2022,12,12,12,12))
                .setContactInfo(contactInfo)
                .build();

        Set<Offer> ownedOffers = new HashSet<>();
        ownedOffers.add(offerFirst);
        ownedOffers.add(offerSecond);

        Set<Offer> favouriteOffers = new HashSet<>(ownedOffers);

        UserAccount userAccount = new UserAccount.UserAccountBuilder()
                .setEmail("username@gmail.com")
                .setEnabled(true)
                .setDateOfRegistration(LocalDateTime.of(2022,12,12,12,12))
                .setPassword("username2002")
                .setRole(Role.USER)
                .setOwnedOffers(ownedOffers)
                .setFavouriteOffers(favouriteOffers)
                .build();

        User user = new User.UserBuilder()
                .setName("User 1")
                .setUsername("Username 1")
                .setDateOfBirth(LocalDate.of(2000,11,11))
                .setUserAccount(userAccount)
                .build();

        user.addAddress(address);


        Report report = new Report.Builder()
                .setDescription("invalid ...")
                .setOffer(offerFirst)
                .setUserAccount(userAccount)
                .build();

        System.out.println("Report: " + report);

        PersistenceManager.saveAll();

    }
}
