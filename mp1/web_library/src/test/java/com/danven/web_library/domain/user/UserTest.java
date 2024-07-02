package com.danven.web_library.domain.user;

import com.danven.web_library.Factory;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.danven.web_library.RecordsUtility.clearRecords;

public class UserTest {

    private static final String VALID_NAME_1 = "John Doe";
    private static final String VALID_USERNAME_1 = "john_doe";

    private static final String VALID_NAME_2 = "Jane Smith";
    private static final String VALID_USERNAME_2 = "jane_smith";

    private static final String INVALID_NAME = "";
    private static final String INVALID_USERNAME = "";

    private static final LocalDate VALID_DATE_OF_BIRTH_1 = LocalDate.of(1990, 1, 1);
    private static final LocalDate VALID_DATE_OF_BIRTH_2 = LocalDate.of(1985, 5, 15);

    private static final LocalDate INVALID_DATE_OF_BIRTH = LocalDate.now().plusDays(1);

    private static final Address VALID_ADDRESS_1 =
            Factory.getAddressWithCityAndDefaultValues("Warsaw");

    private static final Address VALID_ADDRESS_2 =
            Factory.getAddressWithCityAndDefaultValues("Gdansk");

    private static final Address INVALID_ADDRESS_NULL = null;

    @Test
    public void testCreateUserWithValidParameters() {
        User user = new User.UserBuilder()
                .setName(VALID_NAME_1)
                .setUsername(VALID_USERNAME_1)
                .setDateOfBirth(VALID_DATE_OF_BIRTH_1)
                .setAddress(VALID_ADDRESS_1)
                .build();

        Assertions.assertEquals(VALID_NAME_1, user.getName());
        Assertions.assertEquals(VALID_USERNAME_1, user.getUsername());
        Assertions.assertEquals(VALID_DATE_OF_BIRTH_1, user.getDateOfBirth());
    }

    @Test
    public void updateUserWithValidParameters() {
        User user = new User.UserBuilder()
                .setName(VALID_NAME_1)
                .setUsername(VALID_USERNAME_1)
                .setDateOfBirth(VALID_DATE_OF_BIRTH_1)
                .setAddress(VALID_ADDRESS_1)
                .build();

        user.setName(VALID_NAME_2);
        user.setUsername(VALID_USERNAME_2);
        user.setDateOfBirth(VALID_DATE_OF_BIRTH_2);
        user.setAddress(VALID_ADDRESS_2);

        Assertions.assertEquals(VALID_NAME_2, user.getName());
        Assertions.assertEquals(VALID_USERNAME_2, user.getUsername());
        Assertions.assertEquals(VALID_DATE_OF_BIRTH_2, user.getDateOfBirth());
        Assertions.assertEquals(VALID_ADDRESS_2, user.getAddress());
    }

    @Test
    public void createUserWithInvalidNameThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new User.UserBuilder()
                        .setName(INVALID_NAME)
                        .setUsername(VALID_USERNAME_1)
                        .setDateOfBirth(VALID_DATE_OF_BIRTH_1)
                        .setAddress(VALID_ADDRESS_1)
                        .build()
        );
    }

    @Test
    public void createUserWithInvalidUsernameThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new User.UserBuilder()
                        .setName(VALID_NAME_1)
                        .setUsername(INVALID_USERNAME)
                        .setDateOfBirth(VALID_DATE_OF_BIRTH_1)
                        .setAddress(VALID_ADDRESS_1)
                        .build()
        );
    }

    @Test
    public void createUserWithInvalidDateOfBirthThrowsException() {
        Assertions.assertThrows(ValidationException.class,
                () -> new User.UserBuilder()
                        .setName(VALID_NAME_1)
                        .setUsername(VALID_USERNAME_1)
                        .setDateOfBirth(INVALID_DATE_OF_BIRTH)
                        .setAddress(VALID_ADDRESS_1)
                        .build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidName() {

        User user = new User.UserBuilder()
                .setName(VALID_NAME_1)
                .setUsername(VALID_USERNAME_1)
                .setDateOfBirth(VALID_DATE_OF_BIRTH_1)
                .setAddress(VALID_ADDRESS_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> user.setName(INVALID_NAME)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidUsername() {

        User user = new User.UserBuilder()
                .setName(VALID_NAME_1)
                .setUsername(VALID_USERNAME_1)
                .setDateOfBirth(VALID_DATE_OF_BIRTH_1)
                .setAddress(VALID_ADDRESS_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> user.setUsername(INVALID_USERNAME)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidDateOfBirth() {

        User user = new User.UserBuilder()
                .setName(VALID_NAME_1)
                .setUsername(VALID_USERNAME_1)
                .setDateOfBirth(VALID_DATE_OF_BIRTH_1)
                .setAddress(VALID_ADDRESS_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> user.setDateOfBirth(INVALID_DATE_OF_BIRTH)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidAddress() {

        User user = new User.UserBuilder()
                .setName(VALID_NAME_1)
                .setUsername(VALID_USERNAME_1)
                .setDateOfBirth(VALID_DATE_OF_BIRTH_1)
                .setAddress(VALID_ADDRESS_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> user.setAddress(INVALID_ADDRESS_NULL)
        );
    }

    @Test
    public void testCalculateAverageAgeOfUsers(){
        clearRecords();

        User userFirst = new User.UserBuilder()
                .setName(VALID_NAME_1)
                .setUsername(VALID_USERNAME_1)
                .setDateOfBirth(VALID_DATE_OF_BIRTH_1)
                .setAddress(VALID_ADDRESS_1)
                .build();

        User userSecond = new User.UserBuilder()
                .setName(VALID_NAME_2)
                .setUsername(VALID_USERNAME_2)
                .setDateOfBirth(VALID_DATE_OF_BIRTH_2)
                .setAddress(VALID_ADDRESS_2)
                .build();

        int currentYear = LocalDate.now().getYear();

        int ageFirstUser = currentYear - userFirst.getDateOfBirth().getYear();

        int ageSecondUser = currentYear - userSecond.getDateOfBirth().getYear();

        double averageAge = (ageFirstUser + ageSecondUser)/2.0;

        Assertions.assertEquals(averageAge, User.calculateAverageAgeOfUsers());


    }

}
