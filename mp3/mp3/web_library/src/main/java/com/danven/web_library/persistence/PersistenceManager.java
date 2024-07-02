package com.danven.web_library.persistence;

import com.danven.web_library.domain.book.*;
import com.danven.web_library.domain.offer.ContactInfo;
import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.OfferReport;
import com.danven.web_library.domain.report.Report;
import com.danven.web_library.domain.report.ServiceReport;
import com.danven.web_library.domain.user.Account;
import com.danven.web_library.domain.user.AdminAccount;
import com.danven.web_library.domain.user.UserAccount;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistenceManager {

    private static final String OFFER_REPORTS_FILENAME = "src/main/resources/reports/offer_report.txt";

    private static final String SERVICE_REPORTS_FILENAME = "src/main/resources/reports/service_report.txt";


    private static final Map<Class<?>, String> classFileMap = new HashMap<>();

    private static final String BASE_PATH = "src/main/resources/storage/";

    static {
        classFileMap.put(Category.class, BASE_PATH + "categories.dat");
        classFileMap.put(Book.class, BASE_PATH + "books.dat");
        classFileMap.put(PaperBook.class, BASE_PATH + "paper_books.dat");
        classFileMap.put(DiskBook.class, BASE_PATH + "disk_books.dat");
        classFileMap.put(PaperDiskBook.class, BASE_PATH + "disk_books.dat");
        classFileMap.put(ContactInfo.class, BASE_PATH + "contact_infos.dat");
        classFileMap.put(Offer.class, BASE_PATH + "offers.dat");
        classFileMap.put(Report.class, BASE_PATH + "reports.dat");
        classFileMap.put(Account.class, BASE_PATH + "accounts.dat");
        classFileMap.put(UserAccount.class, BASE_PATH + "user_accounts.dat");
        classFileMap.put(AdminAccount.class, BASE_PATH + "admin_accounts.dat");
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public static void saveOfferReport(OfferReport offerReport){
        try (PrintWriter out = new PrintWriter(new FileWriter(OFFER_REPORTS_FILENAME, true))) {
            out.println(offerReport);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void saveServiceReport(ServiceReport serviceReport){
        try (PrintWriter out = new PrintWriter(new FileWriter(SERVICE_REPORTS_FILENAME, true))) {
            out.println(serviceReport);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


}
