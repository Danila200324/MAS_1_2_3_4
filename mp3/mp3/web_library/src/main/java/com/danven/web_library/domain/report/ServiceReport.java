package com.danven.web_library.domain.report;

import com.danven.web_library.domain.user.UserAccount;
import com.danven.web_library.exceptions.ValidationException;
import com.danven.web_library.persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ServiceReport extends Report {

    private static final List<Report> records = new ArrayList<>();

    private final String browserName;

    private final String browserVersion;

    public ServiceReport(String description, UserAccount userAccount, String browserName, String browserVersion) {
        super(description, userAccount);

        validateBrowserName(browserName);
        validateBrowserVersion(browserVersion);

        this.browserName = browserName;
        this.browserVersion = browserVersion;

        records.add(this);
    }

    @Override
    public void reactOnReport() {
        PersistenceManager.saveServiceReport(this);
    }

    public static List<Report> getRecords(){
        return Collections.unmodifiableList(records);
    }

    private void validateBrowserName(String browserName) {
        if (browserName == null || browserName.isBlank()) {
            throw new ValidationException("Browser name should not be null or empty");
        }
    }

    private void validateBrowserVersion(String browserVersion) {
        if (browserVersion == null || browserVersion.isBlank()) {
            throw new ValidationException("Browser Version should not be null or empty");
        }
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ServiceReport that = (ServiceReport) o;
        return Objects.equals(browserName, that.browserName) && Objects.equals(browserVersion, that.browserVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(browserName, browserVersion);
    }

    @Override
    public String toString() {
        return "ServiceReport{" +
                "browserName='" + browserName + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                '}';
    }
}
