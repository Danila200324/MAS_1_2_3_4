package com.danven.web_library.domain.user;

import com.danven.web_library.Factory;
import com.danven.web_library.domain.address.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.danven.web_library.RecordsUtility.clearRecords;
import static org.junit.jupiter.api.Assertions.*;

public class UserAccountUserAssociationTest {


    @Test
    void testUserAccountCreationLinksUserCorrectly() {
        UserAccount userAccount =
                Factory.getUserAccountWithEmailAndDefaultParameters("danven2018@mail.ru");

        User user = new User.UserBuilder()
                .setName("User name 1")
                .setUsername("User name 1")
                .setDateOfBirth(LocalDate.of(2022, 12, 12))
                .setUserAccount(userAccount)
                .build();

        assertEquals(userAccount, user.getUserAccount(), "User should have attached user account");
        assertEquals(user, userAccount.getUser(), "UserAccount should have user");

    }

    @Test
    void testLinkAnotherUserToUserAccount() {
        UserAccount userAccount =
                Factory.getUserAccountWithEmailAndDefaultParameters("danven2018@mail.ru");

        User user_first = new User.UserBuilder()
                .setName("User name 1")
                .setUsername("User name 1")
                .setDateOfBirth(LocalDate.of(2022, 12, 12))
                .setUserAccount(userAccount)
                .build();

        assertEquals(userAccount, user_first.getUserAccount(), "User first should have attached user account");
        assertEquals(user_first, userAccount.getUser(), "UserAccount should have user first");

        User user_second = new User.UserBuilder()
                .setName("User name 1")
                .setUsername("User name 1")
                .setDateOfBirth(LocalDate.of(2022, 12, 12))
                .setUserAccount(userAccount)
                .build();

        assertEquals(userAccount, user_second.getUserAccount(), "User second should have attached user account");
        assertEquals(user_second, userAccount.getUser(), "UserAccount should have user second");

    }


    @Test
    public void testDeleteUserAccount() {

        clearRecords();

        UserAccount userAccount =
                Factory.getUserAccountWithEmailAndDefaultParameters("danven2018@mail.ru");

        User user_first = new User.UserBuilder()
                .setName("User name 1")
                .setUsername("User name 1")
                .setDateOfBirth(LocalDate.of(2022, 12, 12))
                .setUserAccount(userAccount)
                .build();


        Address address = Factory.getAddressWithCityAndPostalCodeAndDefaultValues("Warsaw", "12345");

        user_first.addAddress(
               address
        );

        assertEquals(userAccount, user_first.getUserAccount(), "User first should have attached user account");
        assertEquals(user_first, userAccount.getUser(), "UserAccount should have user first");
        assertEquals(user_first, userAccount.getUser(), "UserAccount should have user first");
        assertTrue(user_first.getAddresses().containsKey(address.getPostalCode()));

        UserAccount.deleteUserAccount(userAccount);

        assertNull(user_first.getUserAccount(), "User Account in user first should be null");
        assertFalse(User.getRecords().contains(user_first), "User extent should not contain user first");
        assertFalse(UserAccount.getRecords().contains(userAccount), "User extent should not contain user first");
        assertNull(address.getUser());



    }
}
