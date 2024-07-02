package com.danven.web_library.domain.user;

import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.exceptions.ValidationException;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 12L;

    private static final List<Account> records = new ArrayList<>();

    private static final int MINIMUM_ACCESSIBLE_AGE = 18;

    private static final int MAXIMUM_OWNED_OFFERS = 100;

    private final LocalDateTime dateOfRegistration;

    private boolean enabled;

    private User user;

    private String email;

    private String password;

    private Set<Offer> ownedOffers = new HashSet<>();

    private Set<Offer> favouriteOffers = new HashSet<>();

    public Account(LocalDateTime dateOfRegistration, boolean enabled,
                   String email, String password, User user) {

        validateDateOfRegistration(dateOfRegistration);
        validateEmail(email);
        validatePassword(password);
        validateUser(user);

        this.dateOfRegistration = dateOfRegistration;
        this.enabled = enabled;
        this.user = user;
        this.email = email;
        this.password = password;

        records.add(this);
    }


    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



    public User getUser() {
        return user;
    }

    public Set<Offer> getOwnedOffers() {
        return ownedOffers;
    }

    public void setOwnedOffers(Set<Offer> ownedOffers) {
        validateOwnedOffers(this.ownedOffers, ownedOffers);
        this.ownedOffers = ownedOffers;
    }

    public Set<Offer> getFavouriteOffers() {
        return favouriteOffers;
    }

    public void setFavouriteOffers(Set<Offer> favouriteOffers) {
        validateFavouriteOffers(favouriteOffers);
        this.favouriteOffers = favouriteOffers;
    }

    public void setUser(User user) {
        validateUser(user);
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(this.email, email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    public void addFavouriteOffer(Offer offer) {
        validateOffer(offer);
        favouriteOffers.add(offer);
    }

    public void addOwnedOffer(Offer offer) {
        validateOffer(offer);
        if(!(ownedOffers.size() + 1 < MAXIMUM_OWNED_OFFERS)){
            throw new ValidationException("Maximum number of owned offers is " + MAXIMUM_OWNED_OFFERS);
        }
        ownedOffers.add(offer);
    }

    private static void validateDateOfRegistration(LocalDateTime dateOfRegistration) {
        if (dateOfRegistration == null || dateOfRegistration.isAfter(LocalDateTime.now())) {
            throw new ValidationException("Date of registration cannot be null or in the future.");
        }
    }

    private static void validateUser(User user) {
        if (user == null) {
            throw new ValidationException("User cannot be null.");
        }
        if(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears() < MINIMUM_ACCESSIBLE_AGE){
            throw new ValidationException("User under age of " + MINIMUM_ACCESSIBLE_AGE + " years old can't create an account");
        }
    }

    private static void validateOwnedOffers(Set<Offer> currentOwnedOffers, Set<Offer> ownedOffers) {
        if (ownedOffers == null) {
            throw new ValidationException("Owned offers cannot be null.");
        }

        if(ownedOffers.size() >= MAXIMUM_OWNED_OFFERS){
            throw new ValidationException("Maximum number of owned offers is " + MAXIMUM_OWNED_OFFERS);
        }

        if(!ownedOffers.containsAll(currentOwnedOffers)){
            throw new ValidationException("New owned offers should contain the old one");
        }
    }

    private static void validateFavouriteOffers(Set<Offer> favouriteOffers) {
        if (favouriteOffers == null) {
            throw new ValidationException("Favourite offers cannot be null.");
        }
    }

    private static void validateOffer(Offer offer) {
        if (offer == null) {
            throw new ValidationException("Offer can't be null");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || !email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$")) {
            throw new ValidationException("Incorrect format of email");
        }
        validateEmailExistence(email);
    }

    private static void validateEmail(String previousEmail, String email) {
        validateEmail(email);
        if(previousEmail.equals(email)){
            return;
        }
        validateEmailExistence(email);
    }

    private static void validateEmailExistence(String email){
        if(records.stream().anyMatch(userAccount -> userAccount.getEmail().equals(email))){
            throw new ValidationException("User account with such email already present");
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new ValidationException("Password is invalid");
        }
    }

    public static List<Account> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return enabled == that.enabled && Objects.equals(dateOfRegistration, that.dateOfRegistration) && Objects.equals(user, that.user)  && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(ownedOffers, that.ownedOffers) && Objects.equals(favouriteOffers, that.favouriteOffers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfRegistration, enabled, user, email, password, ownedOffers, favouriteOffers);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "dateOfRegistration=" + dateOfRegistration +
                ", enabled=" + enabled +
                ", user=" + user +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ownedOffers=" + ownedOffers +
                ", favouriteOffers=" + favouriteOffers +
                '}';
    }
}
