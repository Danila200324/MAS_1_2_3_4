package com.danven.web_library.domain.user;

import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {

    private static final String VALID_COUNTRY_1 = "CountryA";
    private static final String VALID_CITY_1 = "CityA";
    private static final String VALID_HOUSE_NUMBER_1 = "123";
    private static final String VALID_POSTAL_CODE_1 = "ABC123";

    private static final String VALID_COUNTRY_2 = "CountryB";
    private static final String VALID_CITY_2 = "CityB";
    private static final String VALID_HOUSE_NUMBER_2 = "456";
    private static final String VALID_POSTAL_CODE_2 = "DEF456";

    private static final String INVALID_COUNTRY = "";
    private static final String INVALID_CITY = "";
    private static final String INVALID_HOUSE_NUMBER = "";
    private static final String INVALID_POSTAL_CODE = "";

    @Test
    public void testCreateAddressWithValidParameters() {
        Address address = new Address.AddressBuilder()
                .setCountry(VALID_COUNTRY_1)
                .setCity(VALID_CITY_1)
                .setHouseNumber(VALID_HOUSE_NUMBER_1)
                .setPostalCode(VALID_POSTAL_CODE_1)
                .build();

        Assertions.assertEquals(VALID_COUNTRY_1, address.getCountry());
        Assertions.assertEquals(VALID_CITY_1, address.getCity());
        Assertions.assertEquals(VALID_HOUSE_NUMBER_1, address.getHouseNumber());
        Assertions.assertEquals(VALID_POSTAL_CODE_1, address.getPostalCode());
    }

    @Test
    public void updateAddressWithValidParameters() {
        Address address = new Address.AddressBuilder()
                .setCountry(VALID_COUNTRY_1)
                .setCity(VALID_CITY_1)
                .setHouseNumber(VALID_HOUSE_NUMBER_1)
                .setPostalCode(VALID_POSTAL_CODE_1)
                .build();

        address.setCountry(VALID_COUNTRY_2);
        address.setCity(VALID_CITY_2);
        address.setHouseNumber(VALID_HOUSE_NUMBER_2);
        address.setPostalCode(VALID_POSTAL_CODE_2);

        Assertions.assertEquals(VALID_COUNTRY_2, address.getCountry());
        Assertions.assertEquals(VALID_CITY_2, address.getCity());
        Assertions.assertEquals(VALID_HOUSE_NUMBER_2, address.getHouseNumber());
        Assertions.assertEquals(VALID_POSTAL_CODE_2, address.getPostalCode());
    }

    @Test
    public void createAddressWithInvalidCountryThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Address.AddressBuilder()
                        .setCountry(INVALID_COUNTRY)
                        .setCity(VALID_CITY_1)
                        .setHouseNumber(VALID_HOUSE_NUMBER_1)
                        .setPostalCode(VALID_POSTAL_CODE_1)
                        .build()
        );
    }

    @Test
    public void createAddressWithInvalidCityThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Address.AddressBuilder()
                        .setCountry(VALID_COUNTRY_1)
                        .setCity(INVALID_CITY)
                        .setHouseNumber(VALID_HOUSE_NUMBER_1)
                        .setPostalCode(VALID_POSTAL_CODE_1)
                        .build()
        );
    }

    @Test
    public void createAddressWithInvalidHouseNumberThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Address.AddressBuilder()
                        .setCountry(VALID_COUNTRY_1)
                        .setCity(VALID_CITY_1)
                        .setHouseNumber(INVALID_HOUSE_NUMBER)
                        .setPostalCode(VALID_POSTAL_CODE_1)
                        .build()
        );
    }

    @Test
    public void createAddressWithInvalidPostalCodeThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Address.AddressBuilder()
                        .setCountry(VALID_COUNTRY_1)
                        .setCity(VALID_CITY_1)
                        .setHouseNumber(VALID_HOUSE_NUMBER_1)
                        .setPostalCode(INVALID_POSTAL_CODE)
                        .build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidCountry() {
        Address address = new Address.AddressBuilder()
                .setCountry(VALID_COUNTRY_1)
                .setCity(VALID_CITY_1)
                .setHouseNumber(VALID_HOUSE_NUMBER_1)
                .setPostalCode(VALID_POSTAL_CODE_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> address.setCountry(INVALID_COUNTRY)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidCity() {
        Address address = new Address.AddressBuilder()
                .setCountry(VALID_COUNTRY_1)
                .setCity(VALID_CITY_1)
                .setHouseNumber(VALID_HOUSE_NUMBER_1)
                .setPostalCode(VALID_POSTAL_CODE_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> address.setCity(INVALID_CITY)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidHouseNumber() {
        Address address = new Address.AddressBuilder()
                .setCountry(VALID_COUNTRY_1)
                .setCity(VALID_CITY_1)
                .setHouseNumber(VALID_HOUSE_NUMBER_1)
                .setPostalCode(VALID_POSTAL_CODE_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> address.setHouseNumber(INVALID_HOUSE_NUMBER)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidPostalCode() {
        Address address = new Address.AddressBuilder()
                .setCountry(VALID_COUNTRY_1)
                .setCity(VALID_CITY_1)
                .setHouseNumber(VALID_HOUSE_NUMBER_1)
                .setPostalCode(VALID_POSTAL_CODE_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> address.setPostalCode(INVALID_POSTAL_CODE)
        );
    }
}

