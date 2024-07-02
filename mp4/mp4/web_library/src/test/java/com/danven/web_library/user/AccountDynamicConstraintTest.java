package com.danven.web_library.user;

import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.user.Account;
import com.danven.web_library.domain.user.Address;
import com.danven.web_library.domain.user.User;
import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AccountDynamicConstraintTest {

    private Account account;
    private Offer offer1, offer2;
    private User user;
    private Address address;

    @BeforeEach
    public void setUp() {

        cleanRecords();

        address = new Address("123 Main St", "Anytown", "Anystate", "12345");

        String name = "John Doe";
        String username = "johndoe";
        LocalDate dateOfBirth = LocalDate.now().minusYears(25);
        user = new User(name, username, dateOfBirth, address);

        LocalDateTime dateOfRegistration = LocalDateTime.now().minusDays(1);
        account = new Account(dateOfRegistration, true, "test@example.com", "SecurePassword123", user);


        offer1 = mock(Offer.class);
        offer2 = mock(Offer.class);
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
    public void testAddOwnedOfferSuccess() {
        account.addOwnedOffer(offer1);
        Assertions.assertTrue(account.getOwnedOffers().contains(offer1));
    }

    @Test
    public void testAddOwnedOfferExceedsLimit() {
        for (int i = 0; i < 99; i++) {
            Offer newOffer = mock(Offer.class);
            account.addOwnedOffer(newOffer);
        }
        Assertions.assertThrows(ValidationException.class, () -> {
            account.addOwnedOffer(mock(Offer.class));
        });
    }

    @Test
    public void testSetOwnedOffersSuccess() {
        Set<Offer> newOffers = new HashSet<>();
        newOffers.add(offer1);
        newOffers.add(offer2);
        account.setOwnedOffers(newOffers);
        Assertions.assertEquals(2, account.getOwnedOffers().size());
        Assertions.assertTrue(account.getOwnedOffers().containsAll(newOffers));
    }


    @Test
    public void testSetOwnedOffersRemovingOldOnes() {
        Set<Offer> initialOffers = new HashSet<>();
        initialOffers.add(offer1);
        account.setOwnedOffers(initialOffers);

        Set<Offer> newOffers = new HashSet<>();
        newOffers.add(offer2);
        Assertions.assertThrows(ValidationException.class, () -> account.setOwnedOffers(newOffers));
    }
}
