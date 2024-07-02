package com.danven.web_library;

import com.danven.web_library.domain.book.Book;
import com.danven.web_library.domain.book.Category;
import com.danven.web_library.domain.book.DiskBook;
import com.danven.web_library.domain.book.PaperBook;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.OfferReport;
import com.danven.web_library.domain.report.Report;
import com.danven.web_library.domain.report.ServiceReport;
import com.danven.web_library.domain.user.Account;
import com.danven.web_library.domain.user.AdminAccount;
import com.danven.web_library.domain.user.UserAccount;

import java.lang.reflect.Field;
import java.util.Collection;

public class RecordsUtility {

    public static void clearRecords() {
        Class<?>[] classes = {
                Book.class, Category.class, DiskBook.class,
                PaperBook.class, ContactInfo.class, Offer.class, Report.class, OfferReport.class,
                UserAccount.class, Account.class, AdminAccount.class, ServiceReport.class
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
