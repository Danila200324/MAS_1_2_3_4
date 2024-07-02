package com.danven.web_library.domain.report;

import com.danven.web_library.domain.user.IUserAccount;
import com.danven.web_library.domain.user.UserAccount;
import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Report implements Serializable {

    @Serial
    private static final long serialVersionUID = 9L;

    private static final List<Report> records = new ArrayList<>();

    private final String description;

    private final IUserAccount userAccount;

    public Report(String description, IUserAccount userAccount) {
        validateDescription(description);
        validateUserAccount(userAccount);

        this.description = description;
        this.userAccount = userAccount;

        records.add(this);
    }

    protected abstract void reactOnReport();

    public String getDescription() {
        return description;
    }

    public IUserAccount getUserAccount() {
        return userAccount;
    }

    private static void validateDescription(String description) {
        if (description == null || description.length() < 10) {
            throw new ValidationException("Description is too short or missing");
        }
    }

    private static void validateUserAccount(IUserAccount userAccount) {
        if (userAccount == null) {
            throw new ValidationException("User cannot be null");
        }
    }

    public static List<Report> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(description, report.description) && Objects.equals(userAccount, report.userAccount);
    }


    @Override
    public String toString() {
        return "Report{" +
                "description='" + description + '\'' +
                ", userAccount=" + userAccount +
                '}';
    }
}
