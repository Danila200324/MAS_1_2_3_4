package com.danven.web_library.user;

import com.danven.web_library.domain.user.Account;
import com.danven.web_library.domain.user.Address;
import com.danven.web_library.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class UserOrderedConstraintTest {

    private User user1;

    private User user2;

    @BeforeEach
    void setUp() {

        cleanRecords();

        user1 = new User("John Doe", "johndoe123", LocalDate.of(1990, 1, 1),
                new Address("Street 1", "City", "State", "12345"));
        user2 = new User("Jane Smith", "janesmith456", LocalDate.of(1992, 2, 2),
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
    public void testOrderedConstraintWithoutComparatorAsParameter() {
        Assertions.assertIterableEquals(List.of(user1, user2), User.getRecords());
    }

    @Test
    public void testOrderedConstraintWithComparatorAsParameter(){
        Assertions.assertIterableEquals(
                List.of(user2, user1),
                User.getRecords(Comparator.comparing(User::getDateOfBirth).reversed())
        );
    }

}
