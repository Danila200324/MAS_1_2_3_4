package com.danven.web_library.domain.report;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.danven.web_library.domain.book.Category;
import com.danven.web_library.domain.book.PaperBook;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OfferReportTest {

    private static final String DESCRIPTION = "Illegal name for book";
    private static final UserAccount USER_ACCOUNT = createUserAccount();
    private static final Offer OFFER = createOffer();

    private static UserAccount createUserAccount() {
        return new UserAccount("Name1", "Username1", LocalDate.of(2003, 11, 24));
    }

    private static Offer createOffer() {
        PaperBook book = new PaperBook("Book name 1", 2015, "Description for book 1", "Book author 1", "English",
                Set.of(new Category("Category 1", "Description for category 1")), 2);
        ContactInfo contactInfo = new ContactInfo("username@gmail.com", "35786456", "https://instagram.com/5647");
        return new Offer(LocalDateTime.now(), 12.3, 3, book, contactInfo);
    }

    @BeforeEach
    public void initTestEnvironment() {
        clearReportsDirectory("src/main/resources/reports");
    }

    private void clearReportsDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);
        if (Files.exists(path) && Files.isDirectory(path)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    Files.write(entry, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSuccessfulCreateOfferReport() {
        OfferReport report = new OfferReport(DESCRIPTION, OFFER, USER_ACCOUNT);
        assertEquals(DESCRIPTION, report.getDescription());
        assertEquals(USER_ACCOUNT, report.getUserAccount());
        assertEquals(OFFER, report.getOffer());
    }

    @Test
    public void testReactOnReportMethod() throws IOException {
        OfferReport report = new OfferReport(DESCRIPTION, OFFER, USER_ACCOUNT);
        report.reactOnReport();

        assertFalse(Offer.getRecords().contains(OFFER));
        assertNotEquals(0, Files.size(Paths.get("src/main/resources/reports/offer_report.txt")));
    }
}
