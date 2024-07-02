package com.danven.web_library.domain.user;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 11L;

    private static final List<User> records = new ArrayList<>();

    private String name;

    private String username;

    private LocalDate dateOfBirth;

    private Address address;

    private User(UserBuilder builder) {

        validateName(builder.name);
        validateUsername(builder.username);
        validateDateOfBirth(builder.dateOfBirth);
        validateAddress(builder.address);

        this.name = builder.name;
        this.username = builder.username;
        this.dateOfBirth = builder.dateOfBirth;
        this.address = builder.address;

        records.add(this);
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

    public Address getAddress() {
        return address;
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

    public void setAddress(Address address) {
        validateAddress(address);
        this.address = address;
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

    private static void validateAddress(Address address) {
        if (address == null) {
            throw new ValidationException("Address is invalid");
        }
    }

    public static class UserBuilder {
        private String name;
        private String username;
        private LocalDate dateOfBirth;
        private Address address;

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

        public UserBuilder setAddress(Address address) {
            this.address = address;
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
