package com.danven.web_library.domain.user;

import com.danven.web_library.domain.report.Report;
import com.danven.web_library.exceptions.ValidationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AdminAccount implements IAdminAccount {

    private static final List<AdminAccount> records = new ArrayList<>();

    private final String uniqueKey;

    private final LocalDateTime expirationTimeOfAccount;

    public AdminAccount(String uniqueKey, LocalDateTime expirationTimeOfAccount) {

        validateUniqueKey(uniqueKey);
        validateExpirationTime(expirationTimeOfAccount);

        this.uniqueKey = uniqueKey;
        this.expirationTimeOfAccount = expirationTimeOfAccount;

        records.add(this);
    }

    @Override
    public List<Report> getAllReports() {
        return Report.getRecords();
    }

    private void validateUniqueKey(String uniqueKey) {
        if (uniqueKey == null || uniqueKey.isEmpty()) {
            throw new ValidationException("UniqueKey can't be null or empty");
        }
        if (AdminAccount.records.stream().
                anyMatch(adminAccount -> adminAccount.getUniqueKey().equals(uniqueKey))) {
            throw new ValidationException("UniqueKey is not unique");
        }
    }

    private void validateExpirationTime(LocalDateTime expirationTimeOfAccount) {
        if (expirationTimeOfAccount == null ||
                expirationTimeOfAccount.isBefore(LocalDateTime.now())) {
            throw new ValidationException("Time of expiration can't be null or in the past");
        }
    }

    @Override
    public String getUniqueKey() {
        return uniqueKey;
    }


    @Override
    public LocalDateTime getExpirationTimeOfAccount() {
        return expirationTimeOfAccount;
    }

    public static List<AdminAccount> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminAccount that = (AdminAccount) o;
        return Objects.equals(uniqueKey, that.uniqueKey) && Objects.equals(expirationTimeOfAccount, that.expirationTimeOfAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueKey, expirationTimeOfAccount);
    }

    @Override
    public String toString() {
        return "AdminAccount{" +
                "uniqueKey='" + uniqueKey + '\'' +
                ", expirationTimeOfAccount=" + expirationTimeOfAccount +
                '}';
    }
}
