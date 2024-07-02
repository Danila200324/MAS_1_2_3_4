package com.danven.web_library.user;

import com.danven.web_library.domain.user.Address;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {

    private static final String VALID_COUNTRY = "Poland";

    private static final String VALID_CITY = "WARSAW";

    private static final String VALID_HOUSE_NUMBER = "102r";

    private static final String VALID_POSTAL_CODE = "01257";

    private static final String INVALID_POSTAL_CODE = "1234";

    @Test
    public void testSuccessfulCreationOfAddress() {
        Address address = new Address(
                VALID_COUNTRY,
                VALID_CITY,
                VALID_HOUSE_NUMBER,
                VALID_POSTAL_CODE
        );

        Assertions.assertEquals(VALID_COUNTRY, address.getCountry());
        Assertions.assertEquals(VALID_CITY, address.getCity());
        Assertions.assertEquals(VALID_HOUSE_NUMBER, address.getHouseNumber());
        Assertions.assertEquals(VALID_POSTAL_CODE, address.getPostalCode());

    }

    @Test
    public void testCreationAddressWithInvalidPostalCodeThrowsException() {
        Address address = new Address(
                VALID_COUNTRY,
                VALID_CITY,
                VALID_HOUSE_NUMBER,
                VALID_POSTAL_CODE
        );
        Assertions.assertThrows(ValidationException.class, () -> address.setPostalCode(INVALID_POSTAL_CODE));
    }


}
