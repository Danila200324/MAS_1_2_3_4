package com.danven.web_library.domain.user;

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

    private Address address;

    public User(String name, String username, LocalDate dateOfBirth, Address address) {

        validateName(name);
        validateUsername(username);
        validateDateOfBirth(dateOfBirth);
        validateAddress(address);

        this.name = name;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.address = address;

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
        validateAddress(this.address, address);
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

    private static void validateAddress(Address previousAddress, Address newAddress) {
        validateAddress(newAddress);
        if(previousAddress.getPostalCode().equals(newAddress.getPostalCode())){
            throw new ValidationException("Addresses postal codes should be differ");
        }
    }

    public static List<User> getRecords() {
        List<User> users = new ArrayList<>(records);
        users.sort(Comparator.comparing(User::getDateOfBirth));
        return users;
    }

    public static List<User> getRecords(Comparator<User> userComparator){
        List<User> users = new ArrayList<>(records);
        users.sort(userComparator);
        return users;
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
