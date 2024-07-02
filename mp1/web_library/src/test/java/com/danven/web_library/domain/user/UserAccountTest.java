package com.danven.web_library.domain.user;

import com.danven.web_library.Factory;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserAccountTest {

    private static final LocalDateTime VALID_DATE_OF_REGISTRATION_1 =
            LocalDateTime.of(2022, 12, 12, 12, 12);

    private static final LocalDateTime INVALID_DATE_OF_REGISTRATION_FUTURE = LocalDateTime.now().plusDays(1);

    private static final boolean VALID_ENABLED_STATUS_TRUE = true;

    private static final boolean VALID_ENABLED_STATUS_FALSE = false;

    private static final User VALID_USER_1 =
            Factory.getUserWithUsernameAndDefaultParameters("Username 1");

    private static final User INVALID_USER_NULL = null;

    private static final Role VALID_ROLE_USER = Role.USER;

    private static final Role VALID_ROLE_ADMIN = Role.ADMINISTRATOR;

    private static Set<Offer> VALID_OWNED_OFFERS_1;

    private static Set<Offer> VALID_OWNED_OFFERS_2;

    private static final Set<Offer> INVALID_OWNED_OFFERS_NULL = null;

    private static Set<Offer> VALID_FAVOURITE_OFFERS_1;

    private static Set<Offer> VALID_FAVOURITE_OFFERS_2;

    private static final Set<Offer> INVALID_FAVOURITE_OFFERS_NULL = null;

    private static final String VALID_EMAIL_1 = "danven2018@gmail.com";

    private static final String VALID_EMAIL_2 = "danven@gmail.com";

    private static final String INVALID_EMAIL = "danven.gmail.com";

    private static final String VALID_PASSWORD_1 = "Danven_2018";

    private static final String VALID_PASSWORD_2 = "danka_ven_2018";

    private static final String INVALID_PASSWORD = "dan";

    private static final Role INVALID_ROLE_NULL = null;

    @BeforeEach
    public void initialize() {

        VALID_OWNED_OFFERS_1 = new HashSet<>();
        VALID_OWNED_OFFERS_1.add(
                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(1)
        );

        VALID_OWNED_OFFERS_1.add(
                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(2)
        );

        VALID_OWNED_OFFERS_2 = new HashSet<>();
        VALID_OWNED_OFFERS_2.add(
                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(3)
        );
        VALID_OWNED_OFFERS_2.add(
                Factory.getOfferWithNumberOfCopiesAndDefaultParameters(4)
        );


        VALID_FAVOURITE_OFFERS_1 = new HashSet<>(VALID_OWNED_OFFERS_1);

        VALID_FAVOURITE_OFFERS_2 = new HashSet<>(VALID_OWNED_OFFERS_2);


    }

    @Test
    public void testCreateUserAccountWithValidParameters() {

        UserAccount userAccount = new UserAccount.UserAccountBuilder()
                .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                .setEnabled(VALID_ENABLED_STATUS_TRUE)
                .setUser(VALID_USER_1)
                .setRole(VALID_ROLE_USER)
                .setOwnedOffers(VALID_OWNED_OFFERS_1)
                .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                .setEmail(VALID_EMAIL_1)
                .setPassword(VALID_PASSWORD_1)
                .build();

        Assertions.assertEquals(VALID_DATE_OF_REGISTRATION_1, userAccount.getDateOfRegistration());
        Assertions.assertEquals(VALID_ENABLED_STATUS_TRUE, userAccount.isEnabled());
        Assertions.assertEquals(VALID_USER_1, userAccount.getUser());
        Assertions.assertEquals(VALID_ROLE_USER, userAccount.getRole());
        Assertions.assertEquals(VALID_FAVOURITE_OFFERS_1, userAccount.getFavouriteOffers());
        Assertions.assertEquals(VALID_OWNED_OFFERS_1, userAccount.getOwnedOffers());
    }

    @Test
    public void updateUserAccountWithValidParameters() {
        UserAccount userAccount = new UserAccount.UserAccountBuilder()
                .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                .setEnabled(VALID_ENABLED_STATUS_TRUE)
                .setUser(VALID_USER_1)
                .setRole(VALID_ROLE_USER)
                .setOwnedOffers(VALID_OWNED_OFFERS_1)
                .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                .setEmail(VALID_EMAIL_1)
                .setPassword(VALID_PASSWORD_1)
                .build();

        userAccount.setEnabled(VALID_ENABLED_STATUS_FALSE);
        userAccount.setRole(VALID_ROLE_ADMIN);
        userAccount.setOwnedOffers(VALID_OWNED_OFFERS_2);
        userAccount.setFavouriteOffers(VALID_FAVOURITE_OFFERS_2);
        userAccount.setEmail(VALID_EMAIL_2);
        userAccount.setPassword(VALID_PASSWORD_2);

        Assertions.assertEquals(VALID_ENABLED_STATUS_FALSE, userAccount.isEnabled());
        Assertions.assertEquals(VALID_ROLE_ADMIN, userAccount.getRole());
        Assertions.assertEquals(VALID_FAVOURITE_OFFERS_2, userAccount.getFavouriteOffers());
        Assertions.assertEquals(VALID_OWNED_OFFERS_2, userAccount.getOwnedOffers());
    }

    @Test
    public void createUserAccountWithInvalidDateOfRegistrationThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new UserAccount.UserAccountBuilder()
                        .setDateOfRegistration(INVALID_DATE_OF_REGISTRATION_FUTURE)
                        .setEnabled(VALID_ENABLED_STATUS_TRUE)
                        .setUser(VALID_USER_1)
                        .setRole(VALID_ROLE_USER)
                        .setOwnedOffers(VALID_OWNED_OFFERS_1)
                        .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                        .setEmail(VALID_EMAIL_1)
                        .setPassword(VALID_PASSWORD_1)
                        .build()
        );
    }

    @Test
    public void createUserAccountWithNullUserThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new UserAccount.UserAccountBuilder()
                        .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                        .setEnabled(VALID_ENABLED_STATUS_TRUE)
                        .setUser(INVALID_USER_NULL)
                        .setRole(VALID_ROLE_USER)
                        .setOwnedOffers(VALID_OWNED_OFFERS_1)
                        .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                        .setEmail(VALID_EMAIL_1)
                        .setPassword(VALID_PASSWORD_1)
                        .build()
        );
    }

    @Test
    public void createUserAccountWithInvalidEmailThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new UserAccount.UserAccountBuilder()
                        .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                        .setEnabled(VALID_ENABLED_STATUS_TRUE)
                        .setUser(VALID_USER_1)
                        .setRole(VALID_ROLE_USER)
                        .setOwnedOffers(VALID_OWNED_OFFERS_1)
                        .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                        .setEmail(INVALID_EMAIL)
                        .setPassword(VALID_PASSWORD_1)
                        .build()
        );
    }

    @Test
    public void createUserAccountWithInvalidPasswordThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new UserAccount.UserAccountBuilder()
                        .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                        .setEnabled(VALID_ENABLED_STATUS_TRUE)
                        .setUser(VALID_USER_1)
                        .setRole(VALID_ROLE_USER)
                        .setOwnedOffers(VALID_OWNED_OFFERS_1)
                        .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                        .setEmail(VALID_EMAIL_1)
                        .setPassword(INVALID_PASSWORD)
                        .build()
        );
    }

    @Test
    public void createUserAccountWithInvalidOwnedOffersThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new UserAccount.UserAccountBuilder()
                        .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                        .setEnabled(VALID_ENABLED_STATUS_TRUE)
                        .setUser(VALID_USER_1)
                        .setRole(VALID_ROLE_USER)
                        .setOwnedOffers(INVALID_OWNED_OFFERS_NULL)
                        .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                        .setEmail(VALID_PASSWORD_1)
                        .setPassword(VALID_PASSWORD_1)
                        .build()
        );
    }

    @Test
    public void createUserAccountWithInvalidFavouriteOffersThrowsException() {
        Assertions.assertThrows(ValidationException.class, () ->
                new UserAccount.UserAccountBuilder()
                        .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                        .setEnabled(VALID_ENABLED_STATUS_TRUE)
                        .setUser(VALID_USER_1)
                        .setRole(VALID_ROLE_USER)
                        .setOwnedOffers(VALID_OWNED_OFFERS_1)
                        .setFavouriteOffers(INVALID_FAVOURITE_OFFERS_NULL)
                        .setEmail(VALID_PASSWORD_1)
                        .setPassword(VALID_PASSWORD_1)
                        .build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidRole() {

        UserAccount userAccount = new UserAccount.UserAccountBuilder()
                .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                .setEnabled(VALID_ENABLED_STATUS_TRUE)
                .setUser(VALID_USER_1)
                .setRole(VALID_ROLE_USER)
                .setOwnedOffers(VALID_OWNED_OFFERS_1)
                .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                .setEmail(VALID_EMAIL_1)
                .setPassword(VALID_PASSWORD_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> userAccount.setRole(INVALID_ROLE_NULL)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidEmail() {

        UserAccount userAccount = new UserAccount.UserAccountBuilder()
                .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                .setEnabled(VALID_ENABLED_STATUS_TRUE)
                .setUser(VALID_USER_1)
                .setRole(VALID_ROLE_USER)
                .setOwnedOffers(VALID_OWNED_OFFERS_1)
                .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                .setEmail(VALID_EMAIL_1)
                .setPassword(VALID_PASSWORD_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> userAccount.setEmail(INVALID_EMAIL)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidPassword() {

        UserAccount userAccount = new UserAccount.UserAccountBuilder()
                .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                .setEnabled(VALID_ENABLED_STATUS_TRUE)
                .setUser(VALID_USER_1)
                .setRole(VALID_ROLE_USER)
                .setOwnedOffers(VALID_OWNED_OFFERS_1)
                .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                .setEmail(VALID_EMAIL_1)
                .setPassword(VALID_PASSWORD_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> userAccount.setPassword(INVALID_PASSWORD)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidOwnedOffers() {

        UserAccount userAccount = new UserAccount.UserAccountBuilder()
                .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                .setEnabled(VALID_ENABLED_STATUS_TRUE)
                .setUser(VALID_USER_1)
                .setRole(VALID_ROLE_USER)
                .setOwnedOffers(VALID_OWNED_OFFERS_1)
                .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                .setEmail(VALID_EMAIL_1)
                .setPassword(VALID_PASSWORD_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> userAccount.setOwnedOffers(INVALID_OWNED_OFFERS_NULL)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidFavouriteOffers() {

        UserAccount userAccount = new UserAccount.UserAccountBuilder()
                .setDateOfRegistration(VALID_DATE_OF_REGISTRATION_1)
                .setEnabled(VALID_ENABLED_STATUS_TRUE)
                .setUser(VALID_USER_1)
                .setRole(VALID_ROLE_USER)
                .setOwnedOffers(VALID_OWNED_OFFERS_1)
                .setFavouriteOffers(VALID_FAVOURITE_OFFERS_1)
                .setEmail(VALID_EMAIL_1)
                .setPassword(VALID_PASSWORD_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> userAccount.setFavouriteOffers(INVALID_FAVOURITE_OFFERS_NULL)
        );
    }
}
