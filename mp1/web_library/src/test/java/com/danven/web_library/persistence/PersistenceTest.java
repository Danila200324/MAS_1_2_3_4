package com.danven.web_library.persistence;

import com.danven.web_library.Factory;
import com.danven.web_library.domain.book.*;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.Report;
import com.danven.web_library.domain.user.Address;
import com.danven.web_library.domain.user.User;
import com.danven.web_library.domain.user.UserAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

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


    @Test
    public void testPersistenceForCategory() {
        Category categoryFirst = Factory.getCategory("Science", "Science book...");

        Category categorySecond = Factory.getCategory("Drama", "Drama book...");

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(List.of(categoryFirst, categorySecond), Category.getRecords());

    }

    @Test
    public void testPersistenceForLanguage() {
        Language languageFirst = Factory.getLanguage("ENGLISH", "EN");

        Language languageSecond = Factory.getLanguage("POLISH", "POL");

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(List.of(languageFirst, languageSecond), Language.getRecords());

    }

    @Test
    public void testPersistenceForImage() {

        Image imageFirst = Factory.getImage("src/test/testData/book1(jpeg).jpeg", "jpeg");

        Image imageSecond = Factory.getImage("src/test/testData/book2(jpeg).jpeg", "jpeg");

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(List.of(imageFirst, imageSecond), Image.getRecords());

    }

    @Test
    public void testPersistenceForDBook() {
        DBook dBookFirst =
                Factory.getDBookWithAuthorAndDefaultParameters("Author 1");

        DBook dBookSecond =
                Factory.getDBookWithAuthorAndDefaultParameters("Author 2");

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(List.of(dBookFirst, dBookSecond), DBook.getRecords());
        Assertions.assertIterableEquals(List.of(dBookFirst, dBookSecond), Book.getRecords());
    }

    @Test
    public void testPersistenceForPBook() {
        PBook pBookFirst =
                Factory.getPBookWithAuthorAndDefaultParameters("Author 1");

        PBook pBookSecond =
                Factory.getPBookWithAuthorAndDefaultParameters("Author 2");

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(List.of(pBookFirst, pBookSecond), PBook.getRecords());
        Assertions.assertIterableEquals(List.of(pBookFirst, pBookSecond), Book.getRecords());
    }

    @Test
    public void testPersistenceForBook() {
        PBook pBook =
                Factory.getPBookWithAuthorAndDefaultParameters("Author 1");

        DBook dBook =
                Factory.getDBookWithAuthorAndDefaultParameters("Author 2");

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(List.of(pBook, dBook), Book.getRecords());

        //overloaded
        Assertions.assertIterableEquals(List.of(pBook), Book.getRecords(1));

    }

    @Test
    public void testPersistenceForContactInfo() {
        ContactInfo contactInfoFirst =
                Factory.getContactInfoWithEmailAndDefaultParameters("danven2018@gmai.com");

        ContactInfo contactInfoSecond =
                Factory.getContactInfoWithEmailAndDefaultParameters("danven@gmai.com");

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(
                List.of(contactInfoFirst, contactInfoSecond), ContactInfo.getRecords()
        );
    }

    @Test
    public void testPersistenceForOffer() {
        Offer offer1 =
                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(1);

        Offer offer2 =
                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(2);

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(
                List.of(offer1, offer2), Offer.getRecords()
        );
    }

    @Test
    public void testPersistenceForReport() {

        Report reportFirst = Factory.getReportWithDescriptionAndDefaultValues(
                "Description 1"
        );

        Report reportSecond = Factory.getReportWithDescriptionAndDefaultValues(
                "Description 2"
        );

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(
                List.of(reportFirst, reportSecond), Report.getRecords()
        );
    }


    @Test
    public void testPersistenceForUser() {

        User userFirst = Factory.getUserWithUsernameAndDefaultParameters(
                "username1"
        );

        User userSecond = Factory.getUserWithUsernameAndDefaultParameters(
                "username2"
        );

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(
                List.of(userFirst, userSecond), User.getRecords()
        );
    }

    @Test
    public void testPersistenceForUserAccount() {

        UserAccount userFirst = Factory.getUserAccountWithEmailAndDefaultParameters(
                "username1@gmail.com"
        );

        UserAccount userSecond = Factory.getUserAccountWithEmailAndDefaultParameters(
                "username2@gmail.com"
        );

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(
                List.of(userFirst, userSecond), UserAccount.getRecords()
        );
    }

    @Test
    public void testPersistenceForAddress() {

        Address addressFirst = Factory.getAddressWithCityAndDefaultValues("Warsaw");

        Address addressSecond = Factory.getAddressWithCityAndDefaultValues("Gdansk");

        PersistenceManager.saveAll();

        clearRecords();

        PersistenceManager.loadAll();

        Assertions.assertIterableEquals(
                List.of(addressFirst, addressSecond), Address.getRecords()
        );
    }




}
