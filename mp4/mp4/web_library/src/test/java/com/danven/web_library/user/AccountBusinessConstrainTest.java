package com.danven.web_library.user;

import com.danven.web_library.domain.user.Account;
import com.danven.web_library.domain.user.Address;
import com.danven.web_library.domain.user.User;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class AccountBusinessConstrainTest {

    private User user1, user2;

    @BeforeEach
    void setUp() {

        cleanRecords();

        LocalDateTime registrationDate = LocalDateTime.now().minusDays(100);
        user1 = new User("John Doe", "johndoe123", LocalDate.of(1990, 1, 1),
                new Address("Street 1", "City", "State", "12345"));
        user2 = new User("Jane Smith", "janesmith456", LocalDate.now().minusYears(16),
                new Address("Street 2", "City", "State", "67890"));
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
    public void testSuccessfulCreateAccount(){
        Assertions.assertDoesNotThrow(() -> new Account(LocalDateTime.now().minusDays(100),
                true, "john@example.com", "password1", user1));
    }

    @Test
    public void testCreateAccountWithAgeLessThan18ThrowsException(){
        Assertions.assertThrows(ValidationException.class, () -> new Account(LocalDateTime.now().minusDays(100),
                true, "john@example.com", "password1", user2));
    }

    @Test
    public void testSettingUserToAccountWithAgeLessThan18ThrowsException(){
        Account account = new Account(LocalDateTime.now().minusDays(100),
                true, "john@example.com", "password1", user1);
        Assertions.assertThrows(ValidationException.class, () -> account.setUser(user2));
    }

}
