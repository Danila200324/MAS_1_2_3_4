package com.danven.web_library.persistence;

import com.danven.web_library.Factory;
import com.danven.web_library.domain.book.*;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.Report;
import com.danven.web_library.domain.address.Address;
import com.danven.web_library.domain.user.Role;
import com.danven.web_library.domain.user.User;
import com.danven.web_library.domain.user.UserAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.danven.web_library.RecordsUtility.clearRecords;

public class PersistenceTest {

    @AfterEach
    public void cleanDomainFolder() {
        Path domainFolderPath = Paths.get("src/main/resources/storage");

        if (Files.exists(domainFolderPath) && Files.isDirectory(domainFolderPath)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(domainFolderPath)) {
                for (Path entry : stream) {
                    Files.write(entry, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @BeforeEach
    public void init(){
        clearRecords();
    }


//    @Test
//    public void testPersistenceForCategory() {
//        Category categoryFirst = Factory.getCategory("Science", "Science book...");
//
//        Category categorySecond = Factory.getCategory("Drama", "Drama book...");
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(List.of(categoryFirst, categorySecond), Category.getRecords());
//
//    }
//
//    @Test
//    public void testPersistenceForLanguage() {
//        Language languageFirst = Factory.getLanguage("ENGLISH", "EN");
//
//        Language languageSecond = Factory.getLanguage("POLISH", "POL");
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(List.of(languageFirst, languageSecond), Language.getRecords());
//
//    }
//
//    @Test
//    public void testPersistenceForImage() {
//
//        Image imageFirst = Factory.getImage("src/test/testData/book1(jpeg).jpeg", "jpeg");
//
//        Image imageSecond = Factory.getImage("src/test/testData/book2(jpeg).jpeg", "jpeg");
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(List.of(imageFirst, imageSecond), Image.getRecords());
//
//    }
//
//    @Test
//    public void testPersistenceForDBook() {
//        DBook dBookFirst =
//                Factory.getDBookWithAuthorAndDefaultParameters("Author 1");
//
//        DBook dBookSecond =
//                Factory.getDBookWithAuthorAndDefaultParameters("Author 2");
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(List.of(dBookFirst, dBookSecond), DBook.getRecords());
//        Assertions.assertIterableEquals(List.of(dBookFirst, dBookSecond), Book.getRecords());
//    }
//
//    @Test
//    public void testPersistenceForPBook() {
//        PBook pBookFirst =
//                Factory.getPBookWithAuthorAndDefaultParameters("Author 1");
//
//        PBook pBookSecond =
//                Factory.getPBookWithAuthorAndDefaultParameters("Author 2");
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(List.of(pBookFirst, pBookSecond), PBook.getRecords());
//        Assertions.assertIterableEquals(List.of(pBookFirst, pBookSecond), Book.getRecords());
//    }
//
//    @Test
//    public void testPersistenceForBook() {
//        PBook pBook =
//                Factory.getPBookWithAuthorAndDefaultParameters("Author 1");
//
//        DBook dBook =
//                Factory.getDBookWithAuthorAndDefaultParameters("Author 2");
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(List.of(pBook, dBook), Book.getRecords());
//
//        //overloaded
//        Assertions.assertIterableEquals(List.of(pBook), Book.getRecords(1));
//
//    }
//
//    @Test
//    public void testPersistenceForContactInfo() {
//        ContactInfo contactInfoFirst =
//                Factory.getContactInfoWithEmailAndDefaultParameters("danven2018@gmai.com");
//
//        ContactInfo contactInfoSecond =
//                Factory.getContactInfoWithEmailAndDefaultParameters("danven@gmai.com");
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(
//                List.of(contactInfoFirst, contactInfoSecond), ContactInfo.getRecords()
//        );
//    }
//
//    @Test
//    public void testPersistenceForOffer() {
//        Offer offer1 =
//                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(1);
//
//        Offer offer2 =
//                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(2);
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(
//                List.of(offer1, offer2), Offer.getRecords()
//        );
//    }
//
//    @Test
//    public void testPersistenceForReport() {
//
//        Report reportFirst = Factory.getReportWithDescriptionAndDefaultValues(
//                "Description 1"
//        );
//
//        Report reportSecond = Factory.getReportWithDescriptionAndDefaultValues(
//                "Description 2"
//        );
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(
//                List.of(reportFirst, reportSecond), Report.getRecords()
//        );
//    }
//
//
//    @Test
//    public void testPersistenceForUser() {
//
//        User userFirst = Factory.getUserWithUsernameAndDefaultParameters(
//                "username1"
//        );
//
//        User userSecond = Factory.getUserWithUsernameAndDefaultParameters(
//                "username2"
//        );
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(
//                List.of(userFirst, userSecond), User.getRecords()
//        );
//    }
//
//    @Test
//    public void testPersistenceForUserAccount() {
//
//        UserAccount userFirst = Factory.getUserAccountWithEmailAndDefaultParameters(
//                "username1@gmail.com"
//        );
//
//        UserAccount userSecond = Factory.getUserAccountWithEmailAndDefaultParameters(
//                "username2@gmail.com"
//        );
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(
//                List.of(userFirst, userSecond), UserAccount.getRecords()
//        );
//    }
//
//    @Test
//    public void testPersistenceForAddress() {
//
//        Address addressFirst = Factory.getAddressWithCityAndDefaultValues("Warsaw");
//
//        Address addressSecond = Factory.getAddressWithCityAndDefaultValues("Gdansk");
//
//        PersistenceManager.saveAll();
//
//        clearRecords();
//
//        PersistenceManager.loadAll();
//
//        Assertions.assertIterableEquals(
//                List.of(addressFirst, addressSecond), Address.getRecords()
//        );
//    }


    @Test
    public void serializeTest(){
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

        Set<Category> categorySet = new HashSet<>();
        categorySet.add(categoryScience);
        categorySet.add(categoryDrama);

        Author authorPBook = new Author.AuthorBuilder()
                .setFullName("Author 1")
                .build();

        PBook pBook = new PBook.BookBuilder()
                .setName("Book 1")
                .setDescription("Description for book 1")
                .setNumberOfPages(500)
                .setYearOfPublishing(2020)
                .setCategories(categorySet)
                .setLanguage(languageEnglish)
                .build();

        BookAuthor pBookAuthorBook = new BookAuthor.BookAuthorBuilder()
                .setRole("editor")
                        .setBook(pBook)
                                .setAuthor(authorPBook)
                                        .build();

        pBook.addAuthorBook(pBookAuthorBook);

        pBook.addImage(imageFirst);
        pBook.addImage(imageSecond);



        DBook dBook = new DBook.BookBuilder()
                .setName("Book 2")
                .setDescription("Description for book 2")
                .setYearOfPublishing(2020)
                .setCategories(categorySet)
                .setLanguage(languagePolish)
                .setDurationInHours(50)
                .setDiskFormat(DiskFormat.CD)
                .build();

        dBook.addImage(imageThird);
        dBook.addImage(imageFourth);

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

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

    }








}
