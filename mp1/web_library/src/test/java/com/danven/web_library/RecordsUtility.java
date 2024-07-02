package com.danven.web_library;

import com.danven.web_library.domain.book.*;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.Report;
import com.danven.web_library.domain.user.Address;
import com.danven.web_library.domain.user.User;
import com.danven.web_library.domain.user.UserAccount;

import java.lang.reflect.Field;
import java.util.Collection;

public class RecordsUtility {

    public static void clearRecords() {
        Class<?>[] classes = {
                Book.class, Category.class, DBook.class, Image.class, Language.class,
                PBook.class, ContactInfo.class, Offer.class, Report.class, User.class,
                UserAccount.class, Address.class
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
}
