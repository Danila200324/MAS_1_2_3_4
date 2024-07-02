package com.danven.web_library.domain.user;

import com.danven.web_library.domain.address.Address;
import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 11L;

    private static final List<User> records = new ArrayList<>();

    private String name;

    private String username;

    private LocalDate dateOfBirth;

    private final Map<String, Address> addresses = new HashMap<>();

    //composition from part
    private UserAccount userAccount;

    private User(UserBuilder builder) {

        validateName(builder.name);
        validateUsername(builder.username);
        validateDateOfBirth(builder.dateOfBirth);
        validateUserAccount(builder.userAccount);

        this.name = builder.name;
        this.username = builder.username;
        this.dateOfBirth = builder.dateOfBirth;
        this.userAccount = builder.userAccount;

        userAccount.setUser(this);

        records.add(this);
    }

    void deleteUser() {
        this.userAccount = null;
        records.remove(this);

        Iterator<Map.Entry<String, Address>> iterator = addresses.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Address> entry = iterator.next();
            Address address = entry.getValue();
            iterator.remove();
            address.setUser(null);
        }
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void addAddress(Address address) {
        validateAddress(address);
        if (!addresses.containsKey(address.getPostalCode())) {
            addresses.put(address.getPostalCode(), address);
            address.setUser(this);
        }
    }

    public void removeAddress(String postalCode) {
        if (addresses.containsKey(postalCode)) {
            Address address = addresses.get(postalCode);
            addresses.remove(postalCode);
            address.setUser(null);

        }
    }

    public void updateAddressKey(String oldPostalCode, Address address) {
        validateAddress(address);
        if (addresses.remove(oldPostalCode) != null) {
            addresses.put(address.getPostalCode(), address);
        }
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Map<String, Address> getAddresses() {
        return new HashMap<>(addresses);
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setUsername(String username) {
        validateUsername(username);
        this.username = username;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        validateDateOfBirth(dateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }

    private static void validateUserAccount(UserAccount userAccount) {
        if (userAccount == null) {
            throw new IllegalArgumentException("User account can't be null");
        }
    }

    private static void validateAddress(Address address) {
        if (address == null) {
            throw new ValidationException("Address can't be null");
        }
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name is invalid");
        }
    }

    private static void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidationException("Username is invalid");
        }
    }

    private static void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new ValidationException("Date of birth is invalid");
        }
    }

    public static class UserBuilder {
        private String name;
        private String username;
        private LocalDate dateOfBirth;
        private UserAccount userAccount;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder setUserAccount(UserAccount userAccount) {
            this.userAccount = userAccount;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static double calculateAverageAgeOfUsers() {
        return records.stream()
                .mapToInt(user -> LocalDate.now().getYear() - user.getDateOfBirth().getYear())
                .average().orElse(0.0);
    }

    public static List<User> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(username, user.username) && Objects.equals(dateOfBirth, user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, username, dateOfBirth);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
