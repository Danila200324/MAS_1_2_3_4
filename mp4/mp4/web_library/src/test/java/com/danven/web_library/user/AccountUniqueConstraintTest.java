package com.danven.web_library.user;

import com.danven.web_library.domain.user.Account;
import com.danven.web_library.domain.user.Address;
import com.danven.web_library.domain.user.User;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class AccountUniqueConstraintTest {

    private Account account1, account2;
    private User user1, user2;

    @BeforeEach
    void setUp() {

        cleanRecords();

        LocalDateTime registrationDate = LocalDateTime.now().minusDays(100);
        user1 = new User("John Doe", "johndoe123", LocalDate.of(1990, 1, 1),
                new Address("Street 1", "City", "State", "12345"));
        user2 = new User("Jane Smith", "janesmith456", LocalDate.of(1992, 2, 2),
                new Address("Street 2", "City", "State", "67890"));
        account1 = new Account(registrationDate, true, "john@example.com", "password1", user1);
        account2 = new Account(registrationDate, true, "jane@example.com", "password2", user2);
    }

    private void cleanRecords() {
        Class<?>[] classes = {
                User.class, Account.class
        };
        for (Class<?> clazz : classes) {
            try {
                Field recordsField = clazz.getDeclaredField("records");
                recordsField.setAccessible(true);

                Object value = recordsField.get(null);

                if (value instanceof Collection<?> collection) {
                    collection.clear();
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void testSetValidEmail() {
        String newEmail = "newemail@example.com";
        assertDoesNotThrow(() -> account1.setEmail(newEmail));
        assertEquals(newEmail, account1.getEmail());
    }

    @Test
    void testSetInvalidEmailFormat() {
        String invalidEmail = "john@example";
        Exception exception = assertThrows(ValidationException.class, () -> account1.setEmail(invalidEmail));
        assertTrue(exception.getMessage().contains("Incorrect format of email"));
    }

    @Test
    void testSetDuplicateEmail() {
        String duplicateEmail = "jane@example.com";
        Exception exception = assertThrows(ValidationException.class, () -> account1.setEmail(duplicateEmail));
        assertTrue(exception.getMessage().contains("User account with such email already present"));
    }

}
