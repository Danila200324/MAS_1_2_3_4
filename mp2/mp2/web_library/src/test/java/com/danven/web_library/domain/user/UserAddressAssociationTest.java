package com.danven.web_library.domain.user;

import com.danven.web_library.Factory;
import com.danven.web_library.domain.address.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.danven.web_library.RecordsUtility.clearRecords;
import static org.junit.jupiter.api.Assertions.*;

public class UserAddressAssociationTest {

    private User user;
    private Address address1;
    private Address address2;

    @BeforeEach
    public void setup() {

        clearRecords();

        user = Factory.getUserWithUsernameAndDefaultParameters("Username 1");

        address1 =
                Factory.getAddressWithCityAndPostalCodeAndDefaultValues("Warsaw", "12345");

        address2 =
                Factory.getAddressWithCityAndPostalCodeAndDefaultValues("Krakow", "43134");
    }

    @Test
    public void testAddAddressToUserFromUser() {

        assertTrue(user.getAddresses().isEmpty(), "User should have no addresses initially");

        user.addAddress(address1);

        assertEquals(1, user.getAddresses().size(), "User should contain 1 address after addition");
        assertTrue(user.getAddresses().containsValue(address1), "User should contain the added address");
        assertEquals(user, address1.getUser(), "Address's user reference should point to the correct user");
    }

    @Test
    public void testAddAddressToUserFromAddress() {
        assertTrue(user.getAddresses().isEmpty(), "User should have no addresses initially");

        address1.setUser(user);

        assertEquals(1, user.getAddresses().size(), "User should contain 1 address after addition");
        assertTrue(user.getAddresses().containsValue(address1), "User should contain the added address");
        assertEquals(user, address1.getUser(), "Address's user reference should point to the correct user");
    }

    @Test
    public void testRemoveAddressFromUser() {
        user.addAddress(address1);

        user.removeAddress(address1.getPostalCode());

        assertTrue(user.getAddresses().isEmpty(), "User should have no addresses after removal");
        assertNull(address1.getUser(), "Address's user reference should be null after removal");
    }

    @Test
    public void testRemoveAddressSettingNull() {
        user.addAddress(address1);

        address1.setUser(null);

        assertTrue(user.getAddresses().isEmpty(), "User should have no addresses after removal");
        assertNull(address1.getUser(), "Address's user reference should be null after removal");
    }

    @Test
    public void testUpdateAddressPostalCode() {
        user.addAddress(address1);
        String oldPostalCode = address1.getPostalCode();
        String newPostalCode = "10002";

        address1.setPostalCode(newPostalCode);

        assertFalse(user.getAddresses().containsKey(oldPostalCode), "User should not contain the old postal code");
        assertTrue(user.getAddresses().containsKey(newPostalCode), "User should contain the new postal code");
        assertEquals(address1, user.getAddresses().get(newPostalCode), "User's address map should be updated with the new postal code");
    }

    @Test
    public void testChangeAddressUser() {
        user.addAddress(address1);

        User newUser = new User.UserBuilder()
                .setName("Jane Smith")
                .setUsername("jane.smith")
                .setDateOfBirth(LocalDate.of(1985, 5, 15))
                .setUserAccount(
                        Factory.getUserAccountWithEmailAndDefaultParameters("qwerty@gmail.com")
                )
                .build();

        newUser.addAddress(address1);

        assertFalse(user.getAddresses().containsValue(address1), "Old user should no longer contain the address");
        assertTrue(newUser.getAddresses().containsValue(address1), "New user should now contain the address");
        assertEquals(newUser, address1.getUser(), "Address's user reference should point to the new user");
    }

}

