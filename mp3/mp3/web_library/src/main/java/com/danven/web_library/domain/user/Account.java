package com.danven.web_library.domain.user;

import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.domain.report.Report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Account {

    private final static List<Account> records = new ArrayList<>();

    private IUserAccount userAccount;

    private IAdminAccount adminAccount;

    public Account(IUserAccount userAccount, IAdminAccount adminAccount) {
        this.userAccount = userAccount;
        this.adminAccount = adminAccount;

        records.add(this);
    }

    public Account(IAdminAccount adminAccount) {
        this.adminAccount = adminAccount;

        records.add(this);
    }

    public Account(IUserAccount userAccount) {
        this.userAccount = userAccount;

        records.add(this);
    }

    public void createOfferReport(String description, Offer offer) {
        if (userAccount != null) {
            userAccount.createOfferReport(description, offer);
        } else {
            throw new UnsupportedOperationException("No permission");
        }
    }

    public void addFavoriteOffer(Offer offer) {
        if (userAccount != null) {
            userAccount.addFavoriteOffer(offer);
        } else {
            throw new UnsupportedOperationException("No permission");
        }
    }

    public void removeFavouriteOffer(Offer offer) {
        if (userAccount != null) {
            userAccount.removeFavouriteOffer(offer);
        } else {
            throw new UnsupportedOperationException("No permission");
        }
    }

    public List<Report> getAllReports() {
        if (adminAccount != null) {
            return adminAccount.getAllReports();
        } else {
            throw new UnsupportedOperationException("No permission");
        }
    }

    public IUserAccount getUserAccount() {
        return userAccount;
    }

    public IAdminAccount getAdminAccount() {
        return adminAccount;
    }

    public static List<Account> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(userAccount, account.userAccount) && Objects.equals(adminAccount, account.adminAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccount, adminAccount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "userAccount=" + userAccount +
                ", adminAccount=" + adminAccount +
                '}';
    }
}
