package com.danven.web_library.domain.user;

import com.danven.web_library.RecordsUtility;
import com.danven.web_library.domain.book.Category;
import com.danven.web_library.domain.book.PaperBook;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.OfferReport;
import com.danven.web_library.domain.report.Report;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class AccountTest {

    private UserAccount userAccount;
    private AdminAccount adminAccount;
    private Account account;
    private Offer offer;

    @BeforeEach
    public void setup() {
        RecordsUtility.clearRecords();

        userAccount = new UserAccount("Name1", "Username1", LocalDate.of(2003, 11, 24));
        adminAccount = new AdminAccount("sdjvjasnv", LocalDateTime.now().plusDays(34));
        account = new Account(userAccount, adminAccount);
        offer = createOffer();
    }

    private Offer createOffer() {
        PaperBook book = new PaperBook("Book name 1", 2015, "Description for book 1", "Book author 1", "English",
                Set.of(new Category("Category 1", "Description for category 1")), 2);
        ContactInfo contactInfo = new ContactInfo("username@gmail.com", "35786456", "https://instagram.com/5647");
        return new Offer(LocalDateTime.now(), 12.3, 3, book, contactInfo);
    }

    @Test
    public void testAccountInitialization() {
        Assertions.assertNotNull(account.getUserAccount());
        Assertions.assertNotNull(account.getAdminAccount());
    }

    @Test
    public void testUserAccountFunctionality() {
        Assertions.assertTrue(userAccount.getFavouriteOffers().isEmpty());
        account.addFavoriteOffer(offer);

        Assertions.assertIterableEquals(Set.of(offer), userAccount.getFavouriteOffers());
        account.removeFavouriteOffer(offer);
        Assertions.assertTrue(userAccount.getFavouriteOffers().isEmpty());
        account.createOfferReport("Illegal book", offer);

        Assertions.assertEquals(1, OfferReport.getRecords().size());
        Assertions.assertEquals(1,account.getAdminAccount().getAllReports().size());
    }

    @Test
    public void testAdminAccountFunctionality() {
        account.createOfferReport("Illegal book", offer);
        Assertions.assertFalse(Report.getRecords().isEmpty());
        Assertions.assertEquals(1, account.getAllReports().size());
    }

    @Test
    public void testExceptionWhenUserFunctionalityCalledWithNoUserAccount() {
        Account accountOnlyAdmin = new Account(null, adminAccount);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> accountOnlyAdmin.addFavoriteOffer(offer));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> accountOnlyAdmin.removeFavouriteOffer(offer));
    }

    @Test
    public void testExceptionWhenAdminFunctionalityCalledWithNoAdminAccount() {
        Account accountOnlyUser = new Account(userAccount, null);
        Assertions.assertThrows(UnsupportedOperationException.class, accountOnlyUser::getAllReports);
    }

    @Test
    public void testCallNotExistingRoleAdminThrowsException() {
        Account account = new Account(
                userAccount
        );
        Assertions.assertThrows(UnsupportedOperationException.class, account::getAllReports);
    }

    @Test
    public void testCallNotExistingRoleUserThrowsException() {
        Account account = new Account(
                adminAccount
        );

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> account.createOfferReport(
                        "Illegal book", offer
                ));

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> account.removeFavouriteOffer(
                        offer
                ));

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> account.addFavoriteOffer(
                        offer
                ));
    }

}
