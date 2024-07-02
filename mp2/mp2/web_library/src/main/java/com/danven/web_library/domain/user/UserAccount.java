package com.danven.web_library.domain.user;

import com.danven.web_library.domain.offer.Offer;
import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class UserAccount implements Serializable {

    @Serial
    private static final long serialVersionUID = 12L;

    private static final List<UserAccount> records = new ArrayList<>();

    private final LocalDateTime dateOfRegistration;

    private boolean enabled;

    // composition from whole
    private User user;

    private Role role;

    private String email;

    private String password;

    private Set<Offer> ownedOffers;

    private Set<Offer> favouriteOffers;

    private UserAccount(UserAccountBuilder builder) {

        validateDateOfRegistration(builder.dateOfRegistration);
        validateEmail(builder.email);
        validatePassword(builder.password);
        validateRole(builder.role);
        validateFavouriteOffers(builder.favouriteOffers);
        validateOwnedOffers(builder.ownedOffers);

        this.dateOfRegistration = builder.dateOfRegistration;
        this.enabled = builder.enabled;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.ownedOffers = builder.ownedOffers;
        this.favouriteOffers = builder.favouriteOffers;

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

    void setUser(User newUser) {
        validateUser(newUser);
       if(this.user != null && this.user != newUser){
           this.user.deleteUser();
       }
       this.user = newUser;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        validateRole(role);
        this.role = role;
    }

    public Set<Offer> getOwnedOffers() {
        return ownedOffers;
    }

    public void setOwnedOffers(Set<Offer> ownedOffers) {
        validateOwnedOffers(ownedOffers);
        this.ownedOffers = ownedOffers;
    }

    public Set<Offer> getFavouriteOffers() {
        return favouriteOffers;
    }

    public void setFavouriteOffers(Set<Offer> favouriteOffers) {
        validateFavouriteOffers(favouriteOffers);
        this.favouriteOffers = favouriteOffers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
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
        favouriteOffers.add(offer);
    }

    private static void validateDateOfRegistration(LocalDateTime dateOfRegistration) {
        if (dateOfRegistration == null || dateOfRegistration.isAfter(LocalDateTime.now())) {
            throw new ValidationException("Date of registration cannot be null or in the future.");
        }
    }

    private void validateUser(User user) {
        if (user == null || user.getUserAccount() != this) {
            throw new ValidationException("User is inappropriate or attaches to another account.");
        }
    }

    private static void validateRole(Role role) {
        if (role == null) {
            throw new ValidationException("Role cannot be null.");
        }
    }

    private static void validateOwnedOffers(Set<Offer> ownedOffers) {
        if (ownedOffers == null) {
            throw new ValidationException("Owned offers cannot be null.");
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
    }

    private static void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new ValidationException("Password is invalid");
        }
    }

    public static class UserAccountBuilder {
        private LocalDateTime dateOfRegistration;
        private boolean enabled;
        private User user;
        private Role role;
        private String email;
        private String password;
        private Set<Offer> ownedOffers = new HashSet<>();
        private Set<Offer> favouriteOffers = new HashSet<>();

        public UserAccountBuilder setDateOfRegistration(LocalDateTime dateOfRegistration) {
            this.dateOfRegistration = dateOfRegistration;
            return this;
        }

        public UserAccountBuilder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserAccountBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserAccountBuilder setOwnedOffers(Set<Offer> ownedOffers) {
            this.ownedOffers = ownedOffers;
            return this;
        }

        public UserAccountBuilder setFavouriteOffers(Set<Offer> favouriteOffers) {
            this.favouriteOffers = favouriteOffers;
            return this;
        }

        public UserAccountBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserAccountBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserAccount build() {
            return new UserAccount(this);
        }
    }

    public static List<UserAccount> getRecords() {
        return Collections.unmodifiableList(records);
    }

    public static void deleteUserAccount(UserAccount userAccount){
        records.remove(userAccount);
        userAccount.getUser().deleteUser();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return enabled == that.enabled && Objects.equals(dateOfRegistration, that.dateOfRegistration) && Objects.equals(user, that.user) && role == that.role && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(ownedOffers, that.ownedOffers) && Objects.equals(favouriteOffers, that.favouriteOffers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfRegistration, enabled, user, role, email, password, ownedOffers, favouriteOffers);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "dateOfRegistration=" + dateOfRegistration +
                ", enabled=" + enabled +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ownedOffers=" + ownedOffers +
                ", favouriteOffers=" + favouriteOffers +
                '}';
    }
}
