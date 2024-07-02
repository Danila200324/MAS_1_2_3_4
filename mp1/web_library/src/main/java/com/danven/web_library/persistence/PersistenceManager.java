package com.danven.web_library.persistence;

import com.danven.web_library.domain.book.*;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.Report;
import com.danven.web_library.domain.user.Address;
import com.danven.web_library.domain.user.User;
import com.danven.web_library.domain.user.UserAccount;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class PersistenceManager {

    private static final Map<Class<?>, String> classFileMap = new HashMap<>();
    private static final String BASE_PATH = "src/main/resources/storage/";

    static {
        classFileMap.put(Category.class, BASE_PATH + "categories.dat");
        classFileMap.put(Book.class, BASE_PATH + "books.dat");
        classFileMap.put(PBook.class, BASE_PATH + "paper_books.dat");
        classFileMap.put(DBook.class, BASE_PATH + "disk_books.dat");
        classFileMap.put(Image.class, BASE_PATH + "images.dat");
        classFileMap.put(Language.class, BASE_PATH + "languages.dat");
        classFileMap.put(ContactInfo.class, BASE_PATH + "contact_infos.dat");
        classFileMap.put(Offer.class, BASE_PATH + "offers.dat");
        classFileMap.put(Report.class, BASE_PATH + "reports.dat");
        classFileMap.put(User.class, BASE_PATH + "users.dat");
        classFileMap.put(UserAccount.class, BASE_PATH + "user_accounts.dat");
        classFileMap.put(Address.class, BASE_PATH + "addresses.dat");
    }

    public static void loadAll() {
        classFileMap.forEach(PersistenceManager::loadEntities);
    }

    public static void saveAll() {
        classFileMap.forEach(PersistenceManager::saveEntity);
    }

    private static <T> void loadEntities(Class<T> clazz, String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Field recordsField = clazz.getDeclaredField("records");
            recordsField.setAccessible(true);
            List<T> collection = (List<T>) recordsField.get(null);
            collection.addAll((List<T>) inputStream.readObject());
        } catch (ClassNotFoundException | IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static <T> void saveEntity(Class<T> entityClass, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            Method getAllMethod = entityClass.getDeclaredMethod("getRecords");
            List<T> entities = (List<T>) getAllMethod.invoke(null);
            out.writeObject(entities);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.err.println(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
