package com.danven.web_library.domain.report;

import com.danven.web_library.Factory;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.user.UserAccount;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReportTest {

    private static final String VALID_DESCRIPTION_1 = "Report on issue #1";

    private static final String INVALID_DESCRIPTION_1 = "Too short";

    private static final Offer VALID_OFFER_1 =
            Factory.getOfferWithNumberOfCopiesAndDefaultParameters(1);

    private static final Offer INVALID_OFFER_NULL = null;

    private static final UserAccount VALID_USER_ACCOUNT_1 =
            Factory.getUserAccountWithEmailAndDefaultParameters("danven@gmail.com");

    private static final UserAccount INVALID_USER_ACCOUNT_NULL = null;


    @Test
    public void createReportWithValidParameters() {
        Report report = new Report.Builder()
                .setDescription(VALID_DESCRIPTION_1)
                .setOffer(VALID_OFFER_1)
                .setUserAccount(VALID_USER_ACCOUNT_1)
                .build();

        assertEquals(VALID_DESCRIPTION_1, report.getDescription());
        assertEquals(VALID_OFFER_1, report.getOffer());
        assertEquals(VALID_USER_ACCOUNT_1, report.getUserAccount());
    }


    @Test
    public void createReportWithInvalidDescriptionThrowsException() {
        assertThrows(ValidationException.class,
                () -> new Report.Builder()
                        .setDescription(INVALID_DESCRIPTION_1)
                        .setOffer(VALID_OFFER_1)
                        .setUserAccount(VALID_USER_ACCOUNT_1)
                        .build()
        );
    }

    @Test
    public void createReportWithInvalidOfferThrowsException() {
        assertThrows(ValidationException.class,
                () -> new Report.Builder()
                        .setDescription(VALID_DESCRIPTION_1)
                        .setOffer(INVALID_OFFER_NULL)
                        .setUserAccount(VALID_USER_ACCOUNT_1)
                        .build()
        );
    }

    @Test
    public void createReportWithInvalidUserAccountThrowsException() {
        assertThrows(ValidationException.class,
                () -> new Report.Builder()
                        .setDescription(VALID_DESCRIPTION_1)
                        .setOffer(VALID_OFFER_1)
                        .setUserAccount(INVALID_USER_ACCOUNT_NULL)
                        .build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidDescription() {
        assertThrows(ValidationException.class,
                () -> new Report.Builder()
                        .setDescription(INVALID_DESCRIPTION_1)
                        .setOffer(VALID_OFFER_1)
                        .setUserAccount(VALID_USER_ACCOUNT_1)
                        .build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidOffer() {
        assertThrows(ValidationException.class,
                () -> new Report.Builder()
                        .setDescription(VALID_DESCRIPTION_1)
                        .setOffer(INVALID_OFFER_NULL)
                        .setUserAccount(VALID_USER_ACCOUNT_1)
                        .build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidUserAccount() {
        assertThrows(ValidationException.class,
                () -> new Report.Builder()
                        .setDescription(VALID_DESCRIPTION_1)
                        .setOffer(VALID_OFFER_1)
                        .setUserAccount(INVALID_USER_ACCOUNT_NULL)
                        .build()
        );
    }


}
