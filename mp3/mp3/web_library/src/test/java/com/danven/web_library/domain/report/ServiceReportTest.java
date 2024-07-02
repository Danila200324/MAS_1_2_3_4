package com.danven.web_library.domain.report;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;

import com.danven.web_library.domain.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceReportTest {

    private static final String DESCRIPTION = "Illegal name for book";
    private static final UserAccount USER_ACCOUNT = createUserAccount();
    private static final String BROWSER_NAME = "Google Chrome";
    private static final String BROWSER_VERSION = "1.4";

    private static UserAccount createUserAccount() {
        return new UserAccount("Name1", "Username1", LocalDate.of(2003, 11, 24));
    }

    @BeforeEach
    public void initTestEnvironment() {
        clearReportsDirectory("src/main/resources/reports");
    }

    private void clearReportsDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);
        if (Files.exists(path) && Files.isDirectory(path)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    Files.write(entry, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSuccessfulCreateServiceReport() {
        ServiceReport report = new ServiceReport(DESCRIPTION, USER_ACCOUNT, BROWSER_NAME, BROWSER_VERSION);
        assertEquals(DESCRIPTION, report.getDescription());
        assertEquals(USER_ACCOUNT, report.getUserAccount());
        assertEquals(BROWSER_NAME, report.getBrowserName());
        assertEquals(BROWSER_VERSION, report.getBrowserVersion());
    }

    @Test
    public void testReactOnReportMethod() throws IOException {
        ServiceReport report = new ServiceReport(DESCRIPTION, USER_ACCOUNT, BROWSER_NAME, BROWSER_VERSION);

        report.reactOnReport();

        assertNotEquals(0, Files.size(Paths.get("src/main/resources/reports/service_report.txt")));
    }
}
